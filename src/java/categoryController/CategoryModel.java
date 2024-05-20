/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package categoryController;

/**
 *
 * @author ADMIN
 */
public class CategoryModel {

    private int categoryID;
    private String categoryName;
    private String detail;

    public CategoryModel() {
    }

    public CategoryModel(int categoryID, String categoryName, String detail) {
        this.categoryID = categoryID;
        this.categoryName = categoryName;
        this.detail = detail;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDetail() {
        return detail;
    }

    public void setdetail(String detail) {
        this.detail = detail;
    }

    @Override
    public String toString() {
        return "CategoryModel{" + "categoryID=" + categoryID + ", categoryName=" + categoryName + ", detail=" + detail + '}';
    }

    
}
