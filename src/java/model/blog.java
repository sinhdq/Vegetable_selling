/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author SINHDQ
 */
public class blog {
    private int id;
    private int idCreate;
    private String contents;
    private String blogDetails;
    private String createDate;
    private String image;
    private int id_category;

    public blog() {
    }

    public blog(int id, int idCreate, String contents, String blogDetails, String createDate, String image, int id_category) {
        this.id = id;
        this.idCreate = idCreate;
        this.contents = contents;
        this.blogDetails = blogDetails;
        this.createDate = createDate;
        this.image = image;
        this.id_category = id_category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCreate() {
        return idCreate;
    }

    public void setIdCreate(int idCreate) {
        this.idCreate = idCreate;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getBlogDetails() {
        return blogDetails;
    }

    public void setBlogDetails(String blogDetails) {
        this.blogDetails = blogDetails;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getId_category() {
        return id_category;
    }

    public void setId_category(int id_category) {
        this.id_category = id_category;
    }

    @Override
    public String toString() {
        return "blog{" + "id=" + id + ", idCreate=" + idCreate + ", contents=" + contents + ", blogDetails=" + blogDetails + ", createDate=" + createDate + ", image=" + image + ", id_category=" + id_category + '}';
    }

    
    
}
