/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coastshop.web;

import com.coastshop.factory.ServiceFactory;
import com.coastshop.util.SAPUtil;
import com.coastshop.util.SapWorkbook;
import com.coastshop.util.StoreOutProductInfo;
import com.coastshop.util.StoreOutProductInfoComparator;
import com.coastshop.vo.OutList;
import com.coastshop.vo.ShopinSAP;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.poi.ss.usermodel.Workbook;

/**
 *
 * @author Coast
 */
public class SoToFTPServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        InputStream is = null;
        String host = null;
        String username = null;
        String password = null;
        String dir = null;
        try {

            if (request.getParameter("listid") == null) {
                return;
            }
            //param
            int listid = Integer.parseInt(request.getParameter("listid"));
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            //String date = df.format(new Date());

            OutList outlist = ServiceFactory.getOutListServiceInstance().getById(listid);
            String createDate = df.format(outlist.getDate());
            String shop = ServiceFactory.getShopServiceInstance().find(outlist.getShopid()).getName();
            String brand = ServiceFactory.getBrandServiceInstance().find(outlist.getBrandid()).getName();
            List<StoreOutProductInfo> soiflist = ServiceFactory.getStoreOutServiceInstance().findProductInfosByList(listid);
            Collections.sort(soiflist, new StoreOutProductInfoComparator());
            //TODO TureSum

            //ftp
            ServletConfig config = getServletConfig();
            host = config.getInitParameter("host");
            int port = Integer.parseInt(config.getInitParameter("port"));
            username = config.getInitParameter("username");
            password = config.getInitParameter("password");
            dir = config.getInitParameter("dir");
            String filename = Integer.toString(listid) + "_" + brand + "_" + shop + "_" + createDate + "_上品SAP.xlsx";
            SapWorkbook swb = SAPUtil.getWorkbook(soiflist, request.getServletContext().getRealPath("/"));
            int result = uploadExcel(host, port, username, password, dir, filename, swb.getWorkbook());
            if (result == 1) {
                out.println("<h3>成功</h3>");
                out.println("<p>文件: " + filename + " 上传成功!</p>");
                out.println("<p>总数: " + swb.getSum() + " 件</p>");
                //TODO设置为已上传,不能重复
                ShopinSAP sap = new ShopinSAP();
                sap.setOutlistid(listid);
                int sapid = ServiceFactory.getShopinSAPServiceInstance().insert(sap);
                if (sapid <= 0) {
                    out.println("<h3>数据库SAP表: " + sapid + " ,添加失败!</h3>");
                }
            } else {
                out.println("<h3>" + filename + " 上传失败! 错误代码" + result + "</h3>");
                out.println("<h3>请手动上传：</h3>");
            }
            //msg & err
            if (!swb.getMsg().isEmpty()) {
                out.println("<h1>" + swb.getMsg() + "</h1>");
            }
            if (!swb.getErr().isEmpty()) {
                out.println("<h1>" + swb.getErr() + "</h1>");
            }
            //ftp address
            out.println("<p><a href=\"https://" + host + "\" target=\"_blank\">手动上传地址</a></p>");
            out.println("<p>用户名：" + username + "</p>");
            out.println("<p>密码：" + password + "</p>");
            out.println("<p>目录：" + dir + "</p>");
            out.println("<h3><a href='outlist.do'>返回列表</a></h3>");
            out.println("<h3><a href='index.jsp'>返回首页</a></h3>");

        } catch (Exception e) {
            out.print("<h1>上传失败！</h1>");
            if (host != null && password != null && username != null) {
                out.println("<p><a href=\"https://" + host + "\" target=\"_blank\">手动上传地址</a></p>");
                out.println("<p>用户名：" + username + "</p>");
                out.println("<p>密码：" + password + "</p>");
                out.println("<p>目录：" + dir + "</p>");
            }
            out.println("<h3><a href='outlist.do'>返回列表</a></h3>");
            out.println("<h3><a href='index.jsp'>返回首页</a></h3>");
            e.printStackTrace();
        } finally {
            if (is != null) {
                is.close();
            }
            out.close();
        }
    }

    /**
     * 上传到ftp
     *
     * @param host String
     * @param port int
     * @param username String
     * @param password String
     * @param fileDIR String
     * @param saveName String
     * @param wb Workbook
     * @return 1:success; -1:fail to connect; -2:fail to login; -3:fail to
     * change dir; -4:fail to set type; -5:fail to sava file to server.
     * @throws Exception
     */
    private int uploadExcel(String host, int port, String username, String password, String fileDIR, String saveName, Workbook wb) throws Exception {
        int result = 1;
        OutputStream os = null;
        FTPClient ftpClient = new FTPClient();
        ftpClient.setControlEncoding("UTF-8");
        try {
            int reply;
            ftpClient.connect(host);
            //ftpClient.connect(host, port);
            reply = ftpClient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                //System.out.println("isPositiveCompletion fail");
                ftpClient.disconnect();
                result = -1;
                return result;
            }
            if (!ftpClient.login(username, password)) {
                //System.out.println("login fail");
                result = -2;
                return result;
            }
            if (!ftpClient.changeWorkingDirectory(fileDIR)) {
                //System.out.println("changeWorkingDirectory fail");
                result = -3;
                return result;
            }
            if (!ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE)) {
                //System.out.println("setFileType fail");
                result = -4;
                return result;
            }
            //passive
            ftpClient.enterLocalPassiveMode();
            os = ftpClient.storeFileStream(saveName);
            if (os == null) {
                //System.out.println("get file stream fail");
                result = -5;
                return result;
            }
            wb.write(os);
            os.close();
            if (!ftpClient.completePendingCommand()) {
                //System.out.println("completePendingCommand fail");
                ftpClient.logout();
                ftpClient.disconnect();
                result = -6;
            }
            //FTPFile[] file = ftpClient.listFiles(saveName);
            //System.out.println( file.length + "个文件," + Long.toString(file[0].getSize()) );
            return result;
        } catch (Exception e) {
            throw e;
        } finally {
            if (os != null) {
                os.close();
            }
            if (ftpClient.isConnected()) {
                ftpClient.logout();
                try {
                    ftpClient.disconnect();
                } catch (Exception e) {
                    throw e;
                }
            }
        }
    }

    //
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
