/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coastshop.web;

import com.coastshop.factory.ServiceFactory;
import com.coastshop.util.ProductUtil;
import com.coastshop.util.StoreOutProductInfo;
import com.coastshop.vo.Product;
import com.coastshop.vo.Shop;
import com.coastshop.vo.StoreOut;
import com.coastshop.vo.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Coast
 */
public class AjaxServlet extends HttpServlet {

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
        String method = request.getParameter("method");
        //查询商店
        if (method.equals("getshoplist")) {
            try {
                getShops(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //查询用户
        if (method.equals("getusers")) {
            try {
                getUsers(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        //查询款号
        if (method.equals("getproducts")) {
            try {
                getProducts(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        //查询价格
        if (method.equals("getprice")) {
            try {
                getPrice(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        //获取扫单
        if (method.equals("getsolist")) {
            try {
                getStoreOutProductInfoList(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        //提交价格
        if (method.equals("addproduct")) {
            try {
                addProduct(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        //获得总数
        if (method.equals("getsumbylist")) {
            try {
                getSumByList(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        //扫单页面减数
        if (method.equals("substract")) {
            try {
                substract(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        //end function processRequest
    }

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

    private void getShops(HttpServletRequest request, HttpServletResponse response) throws Exception {
        PrintWriter out = response.getWriter();
        try {
            String keyword = request.getParameter("keyword");
            if (keyword == null) {
                keyword = "";
            }
            List<Shop> list = ServiceFactory.getShopServiceInstance().findLikeName(keyword);
            if (list != null) {
                request.setAttribute("shoplist", list);
                request.getRequestDispatcher("/getShopList.jsp").forward(request, response);
            }
//            StringBuilder sb = new StringBuilder();
//            sb.append("[");
//            for (Iterator<Shop> itr = list.iterator(); itr.hasNext();) {
//                Shop shop = itr.next();
//                sb.append("{'name':'" + shop.getName() + "'},");
//            }
//            sb.append("]");
//            sb.deleteCharAt(sb.length() - 2);//删除最后一个逗号
//            out.write(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            out.close();
        }
    }

    private void getUsers(HttpServletRequest request, HttpServletResponse response) throws Exception {
        PrintWriter out = response.getWriter();
        try {
            String keyword = request.getParameter("keyword");
            if (keyword == null) {
                keyword = "";
            }
            List<User> list = ServiceFactory.getUserServiceInstance().findLikeName(keyword);
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            for (Iterator<User> itr = list.iterator(); itr.hasNext();) {
                User user = itr.next();
                sb.append("{'id':'" + Integer.toString(user.getId()) + "','name':'" + user.getName() + "','privilege':'" + user.getPrivilege() + "'},");
            }
            sb.append("]");
            sb.deleteCharAt(sb.length() - 2);//删除最后一个逗号
            out.write(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            out.close();
        }
    }

    private void getProducts(HttpServletRequest request, HttpServletResponse response) throws Exception {
        PrintWriter out = response.getWriter();
        try {
            String keyword = request.getParameter("keyword");
            if (keyword == null) {
                keyword = "";
            }
            List<Product> list = ServiceFactory.getProductServiceInstance().findLikeSn(keyword);
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            for (Iterator<Product> itr = list.iterator(); itr.hasNext();) {
                Product product = itr.next();
                sb.append("{'id':'" + Integer.toString(product.getId()) + "','sn':'" + product.getSn() + "','price':'" + product.getPrice() + "'},");
            }
            sb.append("]");
            sb.deleteCharAt(sb.length() - 2);//删除最后一个逗号
            out.write(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            out.close();
        }
    }

    private int getPrice(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int price = 0;
        PrintWriter out = response.getWriter();

        try {
            String keyword = request.getParameter("keyword");
            if (keyword == null) {
                keyword = "";
            }
            if (keyword.matches(ProductUtil.BARCODEREGEX2017)) {
                keyword = keyword.substring(0, 12);
            } else {
                keyword = keyword.substring(0, 9);
            }
            Product product = ServiceFactory.getProductServiceInstance().find(keyword);
            if (product != null) {
                price = product.getPrice();
            }
            out.write(Integer.toString(price));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            out.close();
        }

        return price;
    }

    private void getStoreOutList(HttpServletRequest request, HttpServletResponse response) throws Exception {
        PrintWriter out = response.getWriter();

        try {
            String keyword = request.getParameter("keyword");
            String strId = request.getParameter("id");
            int id = 0;
            if (keyword == null) {
                keyword = "";
            }
            if (strId != null) {
                id = Integer.parseInt(strId);
            }
            int listid = Integer.parseInt(keyword);
            List<StoreOut> list = ServiceFactory.getStoreOutServiceInstance().findByList(listid, "ORDER BY ID DESC");
            request.setAttribute("list", list);
            request.setAttribute("lastid", id);
            request.getRequestDispatcher("/getStoreOutList.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            out.close();
        }
    }

    private void getStoreOutProductInfoList(HttpServletRequest request, HttpServletResponse response) throws Exception {
        PrintWriter out = response.getWriter();
        try {
            String keyword = request.getParameter("keyword");
            String strId = request.getParameter("id");
            int id = 0;
            if (keyword == null) {
                keyword = "";
            }
            if (strId != null) {
                id = Integer.parseInt(strId);
            }
            int listid = Integer.parseInt(keyword);
            List<StoreOutProductInfo> list = ServiceFactory.getStoreOutServiceInstance().findProductInfosByList(listid, "order by `id` desc");
            request.setAttribute("list", list);
            request.setAttribute("lastid", id);
            request.getRequestDispatcher("/getStoreOutList.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            out.close();
        }
    }

    private void addProduct(HttpServletRequest request, HttpServletResponse response) throws Exception {
        PrintWriter out = response.getWriter();
        try {
            String sn = request.getParameter("sn");
            int price = Integer.parseInt(request.getParameter("price"));
            Product product = new Product();
            product.setSn(sn);
            product.setPrice(price);
            boolean flag = ServiceFactory.getProductServiceInstance().insert(product);
            if (flag) {
                out.print(Integer.toString(price));
            } else {
                out.print("0");
            }
        } catch (Exception e) {
            out.print("0");
        } finally {
            out.close();
        }
    }

    private void getSumByList(HttpServletRequest request, HttpServletResponse response) throws Exception {
        PrintWriter out = response.getWriter();
        try {
            String keyword = request.getParameter("keyword");
            out.print(ServiceFactory.getStoreOutServiceInstance().getSumByList(Integer.parseInt(keyword)));
        } catch (Exception e) {
            out.print("0");
        } finally {
            out.close();
        }
    }

    private void substract(HttpServletRequest request, HttpServletResponse response) throws Exception {
        PrintWriter out = response.getWriter();
        try {
            String id = request.getParameter("id");
            out.print(ServiceFactory.getStoreOutServiceInstance().subtract(Integer.parseInt(id)));
        } catch (Exception e) {
            out.print("0");
        } finally {
            out.close();
        }
    }
}//end class
