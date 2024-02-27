/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.daoAccount;
import dal.daoBill;
import dal.daoBillItem;
import dal.daoHistory;
import dal.daoProduct;
import dal.encryptionPassword;
import dal.sendMail;

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
import model.history;
import model.item;
import model.product;

/**
 *
 * @author SINHDQ
 */
public class homeController extends HttpServlet {

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
        daoAccount daoacc = new daoAccount();
        encryptionPassword encoding = new encryptionPassword();
        sendMail send = new sendMail();
        HttpSession session = request.getSession();
        daoBill daobill = new daoBill();
        daoBillItem daobitem = new daoBillItem();
        daoHistory daohis = new daoHistory();
        String service = request.getParameter("s");
        if (service == null) {
            service = "homepage";
        }
        try ( PrintWriter out = response.getWriter()) {
            //home page
            if (service.equals("homepage")) {
                session.removeAttribute("notification");
                session.removeAttribute("messregister");
                session.removeAttribute("accountadmin");

                daoProduct dao = new daoProduct();
                Vector<product> listpro = dao.showProduct();
                if (session.getAttribute("account") != null) {
                    account acc = (account) session.getAttribute("account");
                    //write in history system
                    daohis.writeHistory(acc, "Home page");
                    if (daobill.getBillByCusIdAndStatus(acc.getId(), 0) != null) {
                        bill b = daobill.getBillByCusIdAndStatus(acc.getId(), 0);
                        int quantity = daobitem.getQuantityProduct(b.getId());
                        session.setAttribute("quantityproduct", quantity);
                    }
                }
                request.setAttribute("listpro", listpro);
                request.getRequestDispatcher("home.jsp").forward(request, response);
            }

            //login customer
            if (service.equals("login")) {
                session.removeAttribute("notification");
                session.removeAttribute("notifications");
                session.removeAttribute("messregister");
                String user = request.getParameter("username");
                account acc = null;
                if (request.getParameter("password") != null) {
                    String pass = request.getParameter("password");
                    String passencode = encoding.md5(pass);
                    acc = daoacc.getAccountByUserPass(user, passencode);
                }
                if (acc == null) {
                    request.getRequestDispatcher("loginCustomer.jsp").forward(request, response);
                } else {
                    if (acc.isActive() == true && acc.getRole() == 1) {
                        //write in history system
                        daohis.writeHistory(acc, "Login System");
                        session.setAttribute("account", acc);
                        response.sendRedirect("home");
                    } else {
                        request.getRequestDispatcher("loginCustomer.jsp").forward(request, response);
                    }
                }

            }
            //logout customer
            if (service.equals("logout")) {
                if (session.getAttribute("account") != null) {
                    account acc = (account) session.getAttribute("account");
                    //write in history system
                    daohis.writeHistory(acc, "Logout System");
                    //enable all session
                    session.invalidate();
                    response.sendRedirect("home");
                } else {
                    response.sendRedirect("home");
                }
            }

            if (service.equals("register")) {
                String name = request.getParameter("fullname");
                String email = request.getParameter("email");
                String phone = request.getParameter("phone");
                String address = request.getParameter("address");
                String user = request.getParameter("username");
                String pass = request.getParameter("password");
                String repass = request.getParameter("repassword");
                if (name.equals("") || email.equals("") || phone.equals("") || address.equals("") || user.equals("") || pass.equals("") || repass.equals("")) {
                    request.getRequestDispatcher("loginCustomer.jsp").forward(request, response);
                    //response.sendRedirect("home");
                } else {
                    //check password and repassword 
                    if (pass.equals(repass)) {
                        account acc = daoacc.getAccountByUserPass(user, pass);
                        //check username and password has exist or not
                        if (acc != null) {
                            request.getRequestDispatcher("loginCustomer.jsp").forward(request, response);
                        } else {
                            String passencode = encoding.md5(pass);
                            acc = new account(name, email, phone, address, user, passencode, false, 1);
                            daoacc.addAccount(acc);
                            //write in history system
                            daohis.writeHistory(acc, "Create new account " + acc.getUsername());
                            String capcha = send.createCapcha(8);
                            send.send(email, capcha);
                            session.setAttribute("capcha", capcha);
                            acc = daoacc.getAccountByUserPass(user, passencode);
                            session.setAttribute("activeacc", acc);
                            session.setAttribute("option", "register");

                            response.sendRedirect("home?s=checkcode");
                        }
                    } else {
                        request.getRequestDispatcher("loginCustomer.jsp").forward(request, response);
                        //response.sendRedirect("home");
                    }

                }

            }
            if (service.equals("profile")) {
                account acc = (account) session.getAttribute("account");
                if (acc != null) {
                    //write in history system
                    daohis.writeHistory(acc, "View Profile Of " + acc.getUsername());
                    if (request.getParameter("submit") == null) {
                        request.getRequestDispatcher("accountdetail.jsp").forward(request, response);
                    } else {
                        String name = request.getParameter("fullname");
                        String address = request.getParameter("address");
                        String phone = request.getParameter("phone");
                        String email = request.getParameter("email");
                        if (name.equals("") || address.equals("") || phone.equals("") || email.equals("")) {
                            request.getRequestDispatcher("accountdetail.jsp").forward(request, response);
                        } else {
                            account account = new account(acc.getId(), name, email, phone, address, acc.getUsername(), acc.getPassword(), acc.isActive(), acc.getRole());
                            daoacc.updateAccount(account);
                            daohis.writeHistory(account, "Update Profile Of " + account.getUsername());
                            session.setAttribute("account", account);
                            response.sendRedirect("home");
                        }
                    }
                } else {
                    response.sendRedirect("home?s=login");
                }

            }
            if (service.equals("changepassword")) {
                account acc = (account) session.getAttribute("account");
                if (acc == null) {
                    response.sendRedirect("home?s=login");
                } else {
                    //write in history system
                    daohis.writeHistory(acc, "Change password Of " + acc.getUsername());
                    if (request.getParameter("submit") == null) {
                        request.getRequestDispatcher("changepassword.jsp").forward(request, response);
                    } else {
                        String opass = request.getParameter("opassword");
                        String npass = request.getParameter("npassword");
                        String rpass = request.getParameter("rpassword");
                        if (opass.equals("") || npass.equals("") || rpass.equals("")) {
                            request.getRequestDispatcher("changepassword.jsp").forward(request, response);
                        } else {
                            if (acc.getPassword().equals(encoding.md5(opass))) {
                                if (npass.equals(rpass)) {
                                    session.setAttribute("option", "changepassword");
                                    session.setAttribute("passchange", encoding.md5(npass));
                                    String capcha = send.createCapcha(8);
                                    send.send(acc.getEmail(), capcha);
                                    session.setAttribute("capcha", capcha);
//                                    account account = new account(acc.getId(), acc.getFullName(), acc.getEmail(), acc.getPhone(), acc.getAddress(), acc.getUsername(), encoding.md5(npass), true, 1);
//                                    daoacc.updateAccount(account);
                                    response.sendRedirect("home?s=checkcode");
                                } else {
                                    request.getRequestDispatcher("changepassword.jsp").forward(request, response);
                                }
                            } else {
                                request.getRequestDispatcher("changepassword.jsp").forward(request, response);
                            }
                        }
                    }
                }

            }
            if (service.equals("forgotpassword")) {
                if (request.getParameter("submit") == null) {
                    request.getRequestDispatcher("forgotpassword.jsp").forward(request, response);
                } else {
                    if (request.getParameter("username") != null || request.getParameter("mail") != null) {
                        String user = request.getParameter("username");
                        String email = request.getParameter("mail");
                        //check patter mail
                        if (user.equals("") || email.equals("")) {
                            request.getRequestDispatcher("forgotpassword.jsp").forward(request, response);
                        } else {
                            account acc = daoacc.getAccountByUserMail(user, email);
                            if (acc == null) {
                                request.getRequestDispatcher("forgotpassword.jsp").forward(request, response);
                            } else {
                                if (acc.isActive() == true && acc.getRole() == 1) {
                                    String capcha = send.createCapcha(8);
                                    send.send(email, capcha);
                                    session.setAttribute("capcha", capcha);
                                    session.setAttribute("option", "forgotpassword");
                                    session.setAttribute("forgotacc", acc);
                                    response.sendRedirect("home?s=checkcode");
                                } else {
                                    response.sendRedirect("home");
                                }
                            }
                        }
                    } else {
                        request.getRequestDispatcher("forgotpassword.jsp").forward(request, response);
                    }
                }
            }

            if (service.equals("checkcode")) {

                if (session.getAttribute("capcha") == null) {
                    response.sendRedirect("home");
                } else {
                    if (request.getParameter("submit") == null) {
                        request.getRequestDispatcher("checkcode.jsp").forward(request, response);
                    } else {
                        if (request.getParameter("code") == null) {
                            request.getRequestDispatcher("checkcode.jsp").forward(request, response);
                        } else {
                            String code = request.getParameter("code");
                            if (code.equals("")) {
                                request.getRequestDispatcher("checkcode.jsp").forward(request, response);
                            } else {
                                String capcha = (String) session.getAttribute("capcha");
                                if (capcha.equals(code)) {
                                    session.removeAttribute("capcha");
                                    if (session.getAttribute("option") != null) {
                                        String option = (String) session.getAttribute("option");
                                        if (option.equals("forgotpassword")) {
                                            session.removeAttribute("option");
                                            response.sendRedirect("home?s=updatepassword");
                                        } else if (option.equals("register")) {
                                            account activeacc = (account) session.getAttribute("activeacc");
                                            account a = new account(activeacc.getId(), activeacc.getFullName(), activeacc.getEmail(),
                                                    activeacc.getPhone(), activeacc.getAddress(), activeacc.getUsername(), activeacc.getPassword(), true, activeacc.getRole());
                                            daoacc.updateAccount(a);
                                            //write in history system
                                            daohis.writeHistory(a, "Account " + a.getUsername() + " Register Suscessfully");
                                            session.removeAttribute("activeacc");
                                            session.removeAttribute("option");
                                            response.sendRedirect("home?s=login");
                                        } else if (option.equals("changepassword")) {
                                            account acc = (account) session.getAttribute("account");
                                            String passchange = (String) session.getAttribute("passchange");
                                            account account = new account(acc.getId(), acc.getFullName(), acc.getEmail(), acc.getPhone(), acc.getAddress(), acc.getUsername(), passchange, true, 1);
                                            daoacc.updateAccount(account);
                                            //write in history system
                                            daohis.writeHistory(account, "Account " + account.getUsername() + " Change password Suscessfully");
                                            session.removeAttribute("option");
                                            session.removeAttribute("passchange");
                                            response.sendRedirect("home?s=login");
                                        } else {
                                            response.sendRedirect("home");
                                        }
                                    }

                                } else {
                                    request.getRequestDispatcher("checkcode.jsp").forward(request, response);
                                }
                            }
                        }
                    }
                }
            }
            if (service.equals("updatepassword")) {

                if (request.getParameter("submit") == null) {
                    request.getRequestDispatcher("updatepassword.jsp").forward(request, response);
                } else {
                    if (request.getParameter("npassword") == null || request.getParameter("rpassword") == null) {
                        request.getRequestDispatcher("updatepassword.jsp").forward(request, response);
                    } else {
                        String npass = request.getParameter("npassword");
                        String rpass = request.getParameter("rpassword");

                        if (npass.equals("") || rpass.equals("")) {
                            request.getRequestDispatcher("updatepassword.jsp").forward(request, response);
                        } else {
                            if (npass.equals(rpass)) {
                                account facc = (account) session.getAttribute("forgotacc");
                                account a = new account(facc.getId(), facc.getFullName(), facc.getEmail(),
                                        facc.getPhone(), facc.getAddress(), facc.getUsername(), encoding.md5(npass), facc.isActive(), facc.getRole());
                                daoacc.updateAccount(a);

                                daohis.writeHistory(a, "Update Password suscessfully");
                                session.removeAttribute("forgotacc");
                                response.sendRedirect("home?s=login");
                            } else {
                                request.getRequestDispatcher("updatepassword.jsp").forward(request, response);
                            }
                        }
                    }
                }
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
