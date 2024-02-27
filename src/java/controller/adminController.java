/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.daoAccount;
import dal.daoBlog;
import dal.daoContact;
import dal.daoCustomerBill;
import dal.daoDepartment;
import dal.daoHistory;
import dal.daoItem;
import dal.daoProduct;
import dal.encryptionPassword;
import dal.sendMail;
import jakarta.servlet.ServletContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Vector;
import model.account;
import model.bill;
import model.blog;
import model.contact;
import model.customerBill;
import model.department;
import model.history;
import model.item;
import model.product;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author SINHDQ
 */
@MultipartConfig
public class adminController extends HttpServlet {

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
        daoAccount daoacc = new daoAccount();
        daoHistory daohis = new daoHistory();
        daoBlog daoblog = new daoBlog();
        daoProduct daopro = new daoProduct();
        daoDepartment daodepart = new daoDepartment();
        daoCustomerBill daoc = new daoCustomerBill();
        daoItem daoitem = new daoItem();
        daoContact daocon = new daoContact();
        sendMail send = new sendMail();
        encryptionPassword encoding = new encryptionPassword();
        String service = request.getParameter("s");
        account account = (account) session.getAttribute("accountadmin");
        if (service == null) {
            service = "productmanager";
        }
        try ( PrintWriter out = response.getWriter()) {
            if (service.equals("productmanager")) {
                if (account == null) {
                    response.sendRedirect("admin?s=loginadmin");
                } else {
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

                    daohis.writeHistory(account, "Product Manager");

                    Vector<product> listpro = daopro.getProductByNameAndDepart(input, namedepart);
                    session.setAttribute("listpro", listpro);
                    request.getRequestDispatcher("productmanager.jsp").forward(request, response);
                }
            }
            if (service.equals("detailproduct")) {
                int pid = Integer.parseInt(request.getParameter("id"));
                product pro = daopro.FindById(pid);
                session.setAttribute("paddetail", pro);

                daohis.writeHistory(account, "View Product Detail");

                request.getRequestDispatcher("productdetailad.jsp").forward(request, response);
            }
            if (service.equals("addproduct")) {
                if (account == null) {
                    response.sendRedirect("admin?s=loginadmin");
                } else {
                    if (request.getParameter("submit") == null) {
                        request.getRequestDispatcher("addproduct.jsp").forward(request, response);
                    } else {
                        if (request.getParameter("pname") == null || request.getParameter("department") == null
                                || request.getParameter("quantity") == null || request.getParameter("price") == null
                                || request.getPart("image") == null || request.getParameter("description") == null) {
                            request.getRequestDispatcher("addproduct.jsp").forward(request, response);
                        } else {
                            String name = request.getParameter("pname");
                            int iddepart = Integer.parseInt(request.getParameter("department"));
                            int quantity = Integer.parseInt(request.getParameter("quantity"));
                            double price = Double.parseDouble(request.getParameter("price"));
                            Part image = request.getPart("image");
                            String path = "/image/" + image.getSubmittedFileName();
                            String realPath = request.getServletContext().getRealPath(path);
                            image.write(realPath);
                            String des = request.getParameter("description");
                            product pro = new product(iddepart, name, price, quantity, image.getSubmittedFileName(), des);
                            daohis.writeHistory(account, "Add New Product with name " + pro.getProductName());
                            daopro.addProduct(pro);
                            response.sendRedirect("admin?s=productmanager");
                        }
                    }
                }
            }
            if (service.equals("adddepartment")) {
                if (account == null) {
                    response.sendRedirect("admin?s=loginadmin");
                } else {
                    if (request.getParameter("submit") == null) {
                        request.getRequestDispatcher("adddepartment.jsp").forward(request, response);
                    } else {
                        if (request.getParameter("depart") == null) {
                            request.getRequestDispatcher("adddepartment.jsp").forward(request, response);
                        } else {
                            String namedepart = request.getParameter("depart");
                            if (namedepart.equals("")) {
                                request.getRequestDispatcher("adddepartment.jsp").forward(request, response);
                            } else {
                                department depart = new department(namedepart);
                                daodepart.addDepartment(depart);
                                Vector<department> listdepart = daodepart.showDepart();
                                session.setAttribute("listdepart", listdepart);

                                daohis.writeHistory(account, "Add New Department with name is " + depart.getDepartName());

                                request.getRequestDispatcher("adddepartment.jsp").forward(request, response);
                            }
                        }
                    }

                }
            }
            if (service.equals("deletedepartment")) {
                if (account == null) {
                    response.sendRedirect("admin?s=loginadmin");
                } else {
                    int did = Integer.parseInt(request.getParameter("id"));
                    daodepart.deleteDepartment(did);
                    Vector<department> listdepart = daodepart.showDepart();

                    daohis.writeHistory(account, "Delete Department " + daodepart.getDepartById(did).getDepartName());

                    session.setAttribute("listdepart", listdepart);
                    response.sendRedirect("admin?s=adddepartment");
                }
            }
            if (service.equals("deleteproduct")) {
                if (account == null) {
                    response.sendRedirect("admin?s=loginadmin");
                } else {

                    if (request.getParameter("did") == null) {
                        int pid = Integer.parseInt(request.getParameter("id"));
                        product pro = daopro.FindById(pid);
                        session.setAttribute("dproduct", pro);
                        request.getRequestDispatcher("deleteproduct.jsp").forward(request, response);
                    } else {
                        int did = Integer.parseInt(request.getParameter("did"));
                        daopro.deleteProduct(did);
                        daohis.writeHistory(account, "Delete Product " + daopro.FindById(did).getProductName());
                        session.removeAttribute("dproduct");
                        response.sendRedirect("admin?s=productmanager");
                    }
                }
            }
            if (service.equals("updateproduct")) {
                if (account == null) {
                    response.sendRedirect("admin?s=loginadmin");
                } else {
                    if (request.getParameter("submit") == null) {
                        int pid = Integer.parseInt(request.getParameter("id"));
                        product product = daopro.FindById(pid);
                        session.setAttribute("uproduct", product);
                        request.getRequestDispatcher("updateproduct.jsp").forward(request, response);
                    } else {
                        if (request.getParameter("pname") == null || request.getParameter("department") == null
                                || request.getParameter("quantity") == null || request.getParameter("price") == null
                                || request.getPart("image") == null || request.getParameter("description") == null) {
                            request.getRequestDispatcher("updateproduct.jsp").forward(request, response);
                        } else {
                            String name = request.getParameter("pname");
                            int iddepart = Integer.parseInt(request.getParameter("department"));
                            int pid = Integer.parseInt(request.getParameter("pid"));
                            product product = daopro.FindById(pid);
                            int quantity = Integer.parseInt(request.getParameter("quantity"));
                            double price = Double.parseDouble(request.getParameter("price"));
                            String des = request.getParameter("description");
                            Part image = request.getPart("image");
                            String path = "/image/" + image.getSubmittedFileName();
                            String realPath = request.getServletContext().getRealPath(path);
                            request.getServletContext().removeAttribute("/image/" + product.getImage());
                            image.write(realPath);
                            product pro = new product(pid, iddepart, name, price, quantity, image.getSubmittedFileName(), des);
                            daopro.updateProduct(pro);

                            daohis.writeHistory(account, "Update Product " + pro.getProductName());

                            response.sendRedirect("admin?s=productmanager");
                        }
                    }
                }
            }

            if (service.equals("accountmanager")) {
                if (account == null) {
                    response.sendRedirect("admin?s=loginadmin");
                } else {
                    String input = request.getParameter("fname");
                    if (input == null) {
                        input = "";
                    }
                    Vector<account> listacc = daoacc.getAccountByName(input);
                    session.setAttribute("listacc", listacc);
                    daohis.writeHistory(account, "Account manager");
                    request.getRequestDispatcher("accountmanager.jsp").forward(request, response);
                }
            }

            if (service.equals("updateactive")) {
                if (account == null) {
                    response.sendRedirect("admin?s=loginadmin");
                } else {
                    int aid = Integer.parseInt(request.getParameter("id"));
                    account a = daoacc.getAccountById(aid);
                    if (a.isActive() == true) {
                        account acc = new account(a.getId(), a.getFullName(), a.getEmail(), a.getPhone(), a.getAddress(), a.getUsername(), a.getPassword(), false, a.getRole());
                        daoacc.updateAccount(acc);
                        daohis.writeHistory(account, "Disable Account " + acc.getUsername());
                        response.sendRedirect("admin?s=accountmanager");
                    } else {
                        account acc = new account(a.getId(), a.getFullName(), a.getEmail(), a.getPhone(), a.getAddress(), a.getUsername(), a.getPassword(), true, a.getRole());
                        daoacc.updateAccount(acc);
                        daohis.writeHistory(account, "Enable Account " + acc.getUsername());
                        response.sendRedirect("admin?s=accountmanager");
                    }
                }
            }

            if (service.equals("managerbill")) {
                if (account == null) {
                    response.sendRedirect("admin?s=loginadmin");
                } else {
                    Vector<customerBill> list = daoc.getBill();
                    session.setAttribute("listbill", list);
                    daohis.writeHistory(account, "Bill Manager");
                    request.getRequestDispatcher("billmanager.jsp").forward(request, response);
                }
            }

            if (service.equals("billDetail")) {
                if (account == null) {
                    response.sendRedirect("admin?s=loginadmin");
                } else {
                    int idb = Integer.parseInt(request.getParameter("idb"));
                    int cusid = Integer.parseInt(request.getParameter("idcus"));
                    Vector<item> detailcart = daoitem.getCartByBillIdAndCusId(idb, cusid);
                    session.setAttribute("cartdetail", detailcart);
                    daohis.writeHistory(account, "View Bill Details of " + daoacc.getAccountById(cusid).getUsername());
                    request.getRequestDispatcher("cartdetail.jsp").forward(request, response);
                }

            }

            if (service.equals("contactmanager")) {
                if (account == null) {
                    response.sendRedirect("admin?s=loginadmin");
                } else {
                    String input = request.getParameter("fname");
                    if (input == null) {
                        input = "";
                    }
                    Vector<contact> list = daocon.getContactByName(input);
                    session.setAttribute("listcon", list);
                    daohis.writeHistory(account, "Contact Manager");
                    request.getRequestDispatcher("contactmanager.jsp").forward(request, response);
                }
            }

            if (service.equals("updatecontact")) {
                if (account == null) {
                    response.sendRedirect("admin?s=loginadmin");
                } else {
                    int cid = Integer.parseInt(request.getParameter("id"));
                    contact ct = daocon.getContactById(cid);
                    if (ct.isStatus() == false) {
                        contact con = new contact(ct.getId(), ct.getFullName(), ct.getEmail(), ct.getMessage(), true, ct.getIdaccount());
                        daocon.updateContact(con);
                        daohis.writeHistory(account, "Process complete Contact Of user " + daoacc.getAccountById(ct.getIdaccount()));
                        response.sendRedirect("admin?s=contactmanager");
                    }
                }
            }

            if (service.equals("blogmanager")) {
                if (account == null) {
                    response.sendRedirect("admin?s=loginadmin");
                } else {
                    String input = request.getParameter("fname");
                    Vector<account> listacc = daoacc.showAccount();
                    if (input == null) {
                        input = "";
                    }
                    Vector<blog> list = daoblog.showBlog(input);
                    session.setAttribute("listacc", listacc);
                    session.setAttribute("listblog", list);
                    daohis.writeHistory(account, "Blog Manager");
                    request.getRequestDispatcher("blogmanager.jsp").forward(request, response);
                }

            }

            if (service.equals("loginadmin")) {
                session.removeAttribute("account");
                String user = request.getParameter("username");
                account acc = null;
                if (request.getParameter("password") != null) {
                    String pass = request.getParameter("password");
                    String passencode = encoding.md5(pass);
                    acc = daoacc.getAccountByUserPass(user, passencode);
                }
                if (acc == null) {
                    request.getRequestDispatcher("loginadmin.jsp").forward(request, response);
                } else {
                    if (acc.isActive() == true && acc.getRole() == 0) {
                        session.setAttribute("accountadmin", acc);
                        daohis.writeHistory(acc, acc.getUsername() + " Login System");
                        response.sendRedirect("admin?s=productmanager");
                    } else {
                        request.getRequestDispatcher("loginadmin.jsp").forward(request, response);
                    }
                }
            }
            if (service.equals("logoutadmin")) {
                if (account != null) {

                    daohis.writeHistory(account, account.getUsername() + " Logout System");

                    session.invalidate();
                    response.sendRedirect("admin?s=loginadmin");
                } else {
                    response.sendRedirect("admin?s=loginadmin");
                }
            }

            if (service.equals("forgotpassword")) {
                if (request.getParameter("submit") == null) {
                    request.getRequestDispatcher("forgotpasswordad.jsp").forward(request, response);
                } else {
                    if (request.getParameter("username") != null || request.getParameter("mail") != null) {
                        String user = request.getParameter("username");
                        String email = request.getParameter("mail");
                        //check patter mail
                        if (user.equals("") || email.equals("")) {
                            request.getRequestDispatcher("forgotpasswordad.jsp").forward(request, response);
                        } else {
                            account acc = daoacc.getAccountByUserMail(user, email);
                            if (acc == null) {
                                request.getRequestDispatcher("forgotpasswordad.jsp").forward(request, response);
                            } else {
                                if (acc.getRole() == 0) {
                                    String capcha = send.createCapcha(8);
                                    send.send(email, capcha);
                                    session.setAttribute("capchaad", capcha);

                                    session.setAttribute("forgotaccad", acc);
                                    response.sendRedirect("admin?s=checkcode");
                                } else {
                                    response.sendRedirect("admin?s=loginadmin");
                                }
                            }
                        }
                    } else {
                        request.getRequestDispatcher("forgotpasswordad.jsp").forward(request, response);
                    }
                }
            }

            if (service.equals("checkcode")) {
                if (session.getAttribute("capchaad") == null) {
                    response.sendRedirect("admin?s=loginadmin");
                } else {
                    if (request.getParameter("submit") == null) {
                        request.getRequestDispatcher("checkcodead.jsp").forward(request, response);
                    } else {
                        if (request.getParameter("code") == null) {
                            request.getRequestDispatcher("checkcodead.jsp").forward(request, response);
                        } else {
                            String code = request.getParameter("code");
                            if (code.equals("")) {
                                request.getRequestDispatcher("checkcodead.jsp").forward(request, response);
                            } else {
                                String capcha = (String) session.getAttribute("capchaad");
                                if (capcha.equals(code)) {
                                    session.removeAttribute("capchaad");
                                    response.sendRedirect("admin?s=updatepassword");
                                }
                            }
                        }
                    }
                }
            }
            if (service.equals("updatepassword")) {
                if (request.getParameter("submit") == null) {
                    request.getRequestDispatcher("updatepasswordad.jsp").forward(request, response);
                } else {
                    if (request.getParameter("npassword") == null || request.getParameter("rpassword") == null) {
                        request.getRequestDispatcher("updatepasswordad.jsp").forward(request, response);
                    } else {
                        String npass = request.getParameter("npassword");
                        String rpass = request.getParameter("rpassword");

                        if (npass.equals("") || rpass.equals("")) {
                            request.getRequestDispatcher("updatepasswordad.jsp").forward(request, response);
                        } else {
                            if (npass.equals(rpass)) {
                                account facc = (account) session.getAttribute("forgotaccad");
                                account a = new account(facc.getId(), facc.getFullName(), facc.getEmail(), facc.getPhone(), facc.getAddress(), facc.getUsername(), encoding.md5(npass), facc.isActive(), facc.getRole());
                                daoacc.updateAccount(a);
                                session.removeAttribute("forgotaccad");
                                daohis.writeHistory(a, a.getUsername() + " Update Password");
                                response.sendRedirect("admin?s=loginadmin");
                            } else {
                                request.getRequestDispatcher("updatepasswordad.jsp").forward(request, response);
                            }
                        }
                    }
                }
            }

            if (service.equals("history")) {
                if (account == null) {
                    response.sendRedirect("admin?s=loginadmin");
                } else {
                    String input = request.getParameter("nameacc");
                    if (input == null) {
                        input = "";
                    }
                    Vector<history> listhis = daohis.showHisByUserName(input);
                    daohis.writeHistory(account, "View History");
                    session.setAttribute("listhis", listhis);
                    request.getRequestDispatcher("history.jsp").forward(request, response);
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
