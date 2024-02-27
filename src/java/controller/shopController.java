/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.daoBill;
import dal.daoBillItem;
import dal.daoDepartment;
import dal.daoHistory;
import dal.daoItem;
import dal.daoProduct;
import dal.encryptionPassword;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Vector;
import model.account;
import model.bill;
import model.billItem;
import model.department;
import model.history;
import model.item;
import model.product;

/**
 *
 * @author SINHDQ
 */
public class shopController extends HttpServlet {

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
        HttpSession session = request.getSession();
        daoProduct daopro = new daoProduct();
        encryptionPassword encoding = new encryptionPassword();
        daoDepartment daodepart = new daoDepartment();
        daoBill daobill = new daoBill();
        daoHistory daohis = new daoHistory();
        daoBillItem daobitem = new daoBillItem();
        daoItem daoitem = new daoItem();

        String service = request.getParameter("s");
        //get information user
        account acc = (account) session.getAttribute("account");
        if (service == null) {
            service = "shoppage";
        }
        try ( PrintWriter out = response.getWriter()) {
            //shop page
            if (service.equals("shoppage")) {
                session.removeAttribute("notification");
                session.removeAttribute("notifications");
                session.removeAttribute("messregister");

                Vector<department> listdepart = daodepart.showDepart();
                session.setAttribute("listdepart", listdepart);
                String input = request.getParameter("namepro");
                if (input == null) {
                    input = "";
                }
                String namedepart = "";
                if (request.getParameter("iddepart") != null) {
                    int id = Integer.parseInt(request.getParameter("iddepart"));
                    namedepart = daodepart.getDepartById(id).getDepartName();
                }

                if (session.getAttribute("account") != null && daobill.getBillByCusIdAndStatus(acc.getId(), 0) != null) {
                    acc = (account) session.getAttribute("account");

                    bill b = daobill.getBillByCusIdAndStatus(acc.getId(), 0);
                    int quantity = daobitem.getQuantityProduct(b.getId());
                    session.setAttribute("quantityproduct", quantity);
                }
                if (session.getAttribute("account") != null) {
                    if (input.equals("")) {
                        daohis.writeHistory(acc, "Shop page");
                    } else {
                        daohis.writeHistory(acc, "Search with character '" + input + "' in Shop");
                    }
                }
                
                Vector<product> listpro = daopro.getProductByNameAndDepart(input, namedepart);
                session.setAttribute("listpro", listpro);
                request.getRequestDispatcher("shop.jsp").forward(request, response);
            }
            
            //cart
            if (service.equals("cart")) {
                if (acc == null) {
                    response.sendRedirect("home?s=login");
                } else {
                    session.removeAttribute("notification");
                    session.removeAttribute("notifications");
                    if (session.getAttribute("quantityproduct") != null) {
                        
                        Vector<item> listcart = daoitem.listCart(acc.getId());
                        bill b = daobill.getBillByCusIdAndStatus(acc.getId(), 0);
                        double total = daobitem.TotalPriceByBillId(b.getId());
                        daohis.writeHistory(acc, "View Cart");
                        session.setAttribute("listcart", listcart);
                        session.setAttribute("total", total);
                        request.getRequestDispatcher("cart.jsp").forward(request, response);
                    } else {
                        response.sendRedirect("shop");
                    }
                }
            }
            //add to cart
            if (service.equals("addtocart")) {
                if (acc == null) {
                    response.sendRedirect("home?s=login");
                } else {
                    int pid = Integer.parseInt(request.getParameter("id"));
                    product pro = daopro.FindById(pid);
                    //get date now
                    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                    long millis = System.currentTimeMillis();
                    java.sql.Date date = new java.sql.Date(millis);
                    String datenow = df.format(date);
                    bill b = daobill.getBillByCusIdAndStatus(acc.getId(), 0);
                    if (b == null) {
                        bill bll = new bill(acc.getId(), 0, datenow, "", 0);
                        daobill.addBill(bll);
                        b = daobill.getBillByCusIdAndStatus(acc.getId(), 0);
                        //check billitem exist
                        billItem bitem = daobitem.getBillItemByBillidAndProductid(b.getId(), pid);
                        if (bitem == null) {
                            int count = daobitem.CountItemByBillId(b.getId());
                            billItem bitems = new billItem(count + 1, b.getId(), pid, 1, pro.getPrice());
                            daobitem.addItem(bitems);
                            daohis.writeHistory(acc, "Add "+pro.getProductName()+" To Cart");
                        } else {
                            billItem bitems = new billItem(bitem.getItemID(), bitem.getBillID(), bitem.getProductID(), bitem.getQuantity() + 1, pro.getPrice() * (bitem.getQuantity() + 1));
                            daobitem.updateBillItem(bitems);
                            daohis.writeHistory(acc, "Add "+pro.getProductName()+" To Cart");
                        }
                        double total = daobitem.TotalPriceByBillId(b.getId());
                        bill blls = new bill(b.getId(), acc.getId(), total, datenow, "", 0);
                        daobill.updateBill(blls);
                    } else {
                        //check billitem exist
                        billItem bitem = daobitem.getBillItemByBillidAndProductid(b.getId(), pid);
                        if (bitem == null) {
                            int count = daobitem.CountItemByBillId(b.getId());
                            billItem bitems = new billItem(count + 1, b.getId(), pid, 1, pro.getPrice());
                            daobitem.addItem(bitems);
                        } else {
                            billItem bitems = new billItem(bitem.getItemID(), bitem.getBillID(), bitem.getProductID(), bitem.getQuantity() + 1, pro.getPrice() * (bitem.getQuantity() + 1));
                            daobitem.updateBillItem(bitems);
                        }
                        daohis.writeHistory(acc, "Add "+pro.getProductName()+" To Cart");
                        double total = daobitem.TotalPriceByBillId(b.getId());
                        bill blls = new bill(b.getId(), acc.getId(), total, datenow, "", 0);
                        daobill.updateBill(blls);
                    }
                    response.sendRedirect("shop");
                }

            }
//            if (service.equals("addtocartdetail")) {
//                if (acc == null) {
//                    response.sendRedirect("home?s=login");
//                } else {
//                    if (request.getParameter("submit") == null) {
//                        request.getRequestDispatcher("productDetail.jsp").forward(request, response);
//                    } else {
//                        int pid = Integer.parseInt(request.getParameter("id"));
//                        int quantity = Integer.parseInt(request.getParameter("quantity"));
//                        product pro = daopro.FindById(pid);
//                        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//                        long millis = System.currentTimeMillis();
//                        java.sql.Date date = new java.sql.Date(millis);
//                        String datenow = df.format(date);
//                        bill b = daobill.getBillByCusIdAndStatus(acc.getId(), 0);
//                        if (b == null) {
//                            bill bll = new bill(acc.getId(), 0, datenow, "", 0);
//                            daobill.addBill(bll);
//                            b = daobill.getBillByCusIdAndStatus(acc.getId(), 0);
//                            billItem bitem = daobitem.getBillItemByBillidAndProductid(b.getId(), pid);
//                            if (bitem == null) {
//                                int count = daobitem.CountItemByBillId(b.getId());
//                                billItem bitems = new billItem(count + 1, b.getId(), pid, quantity, pro.getPrice());
//                                daobitem.addItem(bitems);
//                            } else {
//                                billItem bitems = new billItem(bitem.getItemID(), bitem.getBillID(), bitem.getProductID(), bitem.getQuantity() + quantity, pro.getPrice() * (bitem.getQuantity() + quantity));
//                                daobitem.updateBillItem(bitems);
//                            }
//                            double total = daobitem.TotalPriceByBillId(b.getId());
//                            bill blls = new bill(b.getId(), acc.getId(), total, datenow, "", 0);
//                            daobill.updateBill(blls);
//                        } else {
//                            //check billitem exist
//                            billItem bitem = daobitem.getBillItemByBillidAndProductid(b.getId(), pid);
//                            if (bitem == null) {
//                                int count = daobitem.CountItemByBillId(b.getId());
//                                billItem bitems = new billItem(count + 1, b.getId(), pid, quantity, pro.getPrice());
//                                daobitem.addItem(bitems);
//                            } else {
//                                billItem bitems = new billItem(bitem.getItemID(), bitem.getBillID(), bitem.getProductID(), bitem.getQuantity() + quantity, pro.getPrice() * (bitem.getQuantity() + quantity));
//                                daobitem.updateBillItem(bitems);
//                            }
//                            double total = daobitem.TotalPriceByBillId(b.getId());
//                            bill blls = new bill(b.getId(), acc.getId(), total, datenow, "", 0);
//                            daobill.updateBill(blls);
//                        }
//                        //response.sendRedirect("shop?s=productdetail&id="+pid);
//                    }
//                }
//            }

            if (service.equals("deleteitem")) {
                int pid = Integer.parseInt(request.getParameter("iditem"));
                bill b = daobill.getBillByCusIdAndStatus(acc.getId(), 0);
                daobitem.deleteBillItem(pid, b.getId());
                
                double total = daobitem.TotalPriceByBillId(b.getId());
                bill blls = new bill(b.getId(), acc.getId(), total, b.getCreateDate(), "", 0);
                daobill.updateBill(blls);

                int quantity = daobitem.getQuantityProduct(b.getId());
                session.setAttribute("quantityproduct", quantity);

                product p = daopro.FindById(pid);
                daohis.writeHistory(acc, "Delete "+p.getProductName()+" Out Cart");
                
                response.sendRedirect("shop?s=cart");
            }
            if (service.equals("checkout")) {
                if (acc != null) {
                    if (request.getParameter("password") != null) {
                        String pass = request.getParameter("password");
                        //check ma hoa
                        String encodepass = encoding.md5(pass);
                        if (acc.getPassword().equals(encodepass)) {
                            session.removeAttribute("message");
                            //get date now
                            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                            long millis = System.currentTimeMillis();
                            java.sql.Date date = new java.sql.Date(millis);
                            String datenow = df.format(date);

                            Vector<item> listcart = daoitem.listCart(acc.getId());
                            bill b = daobill.getBillByCusIdAndStatus(acc.getId(), 0);
                            double total = daobitem.TotalPriceByBillId(b.getId());
                            bill bll = new bill(b.getId(), b.getCustomerID(), total, b.getCreateDate(), datenow, 1);
                            daobill.updateBill(bll);
                            //update quantity product
                            for (item o : listcart) {
                                int id = o.getProduct().getId();
                                int departid = o.getProduct().getDepartID();
                                String name = o.getProduct().getProductName();
                                double price = o.getProduct().getPrice();
                                int quantity = 0;
                                if (o.getProduct().getQuantity() > o.getQuantity()) {
                                    quantity = o.getProduct().getQuantity() - o.getQuantity();
                                }
                                String image = o.getProduct().getImage();
                                String description = o.getProduct().getDescription();
                                product pro = new product(id, departid, name, price, quantity, image, description);

                                daopro.updateProduct(pro);
                            }
                            session.removeAttribute("listcart");
                            session.removeAttribute("total");
                            session.removeAttribute("quantityproduct");
                            
                            daohis.writeHistory(acc, "Pay Cart");
                            
                            response.sendRedirect("shop");
                        } else {
                            String message = "Password invalid. Try again.";
                            session.setAttribute("message", message);
                            request.getRequestDispatcher("checkout.jsp").forward(request, response);
                        }
                    } else {
                        request.getRequestDispatcher("checkout.jsp").forward(request, response);
                    }
                } else {
                    response.sendRedirect("home?s=login");
                }
            }
            if (service.equals("productdetail")) {
                int pid = Integer.parseInt(request.getParameter("id"));
                product pro = daopro.FindById(pid);
                session.setAttribute("pdetail", pro);
                
                daohis.writeHistory(acc, "View "+pro.getProductName()+" Detail");
                
                request.getRequestDispatcher("productDetail.jsp").forward(request, response);
            }
            if (service.equals("updatequantityinc")) {
                int pid = Integer.parseInt(request.getParameter("iditem"));
                bill b = daobill.getBillByCusIdAndStatus(acc.getId(), 0);
                billItem bi = daobitem.getBillItemByBillidAndProductid(b.getId(), pid);
                billItem bii = new billItem(bi.getItemID(), bi.getBillID(), bi.getProductID(), bi.getQuantity() + 1, bi.getTotalPrice() + (bi.getTotalPrice() / bi.getQuantity()));
                daobitem.updateBillItem(bii);

                double total = daobitem.TotalPriceByBillId(b.getId());
                bill blls = new bill(b.getId(), acc.getId(), total, b.getCreateDate(), "", 0);
                daobill.updateBill(blls);

                int quantity = daobitem.getQuantityProduct(b.getId());
                session.setAttribute("quantityproduct", quantity);

                response.sendRedirect("shop?s=cart");
            }

            if (service.equals("updatequantityde")) {
                int pid = Integer.parseInt(request.getParameter("iditem"));
                bill b = daobill.getBillByCusIdAndStatus(acc.getId(), 0);
                billItem bi = daobitem.getBillItemByBillidAndProductid(b.getId(), pid);
                billItem bii = new billItem(bi.getItemID(), bi.getBillID(), bi.getProductID(), bi.getQuantity() - 1, bi.getTotalPrice() - (bi.getTotalPrice() / bi.getQuantity()));
                daobitem.updateBillItem(bii);
                double total = daobitem.TotalPriceByBillId(b.getId());
                bill blls = new bill(b.getId(), acc.getId(), total, b.getCreateDate(), "", 0);
                daobill.updateBill(blls);
                int quantity = daobitem.getQuantityProduct(b.getId());
                session.setAttribute("quantityproduct", quantity);
                
                product pro = daopro.FindById(pid);
                daohis.writeHistory(acc, "Update quantity of "+pro.getProductName());
                
                response.sendRedirect("shop?s=cart");
            }

        }
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

}
