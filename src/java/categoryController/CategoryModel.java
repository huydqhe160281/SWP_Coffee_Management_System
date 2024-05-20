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

    private int categortID;
    private String categoryName;
    private String detail;

    public CategoryModel() {
    }

    public CategoryModel(int categortID, String categoryName, String detail) {
        this.categortID = categortID;
        this.categoryName = categoryName;
        this.detail = detail;
    }

    public int getCategortID() {
        return categortID;
    }

    public void setCategortID(int categortID) {
        this.categortID = categortID;
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
        return "CategoryModel{" + "categortID=" + categortID + ", categoryName=" + categoryName + ", detail=" + detail + '}';
    }

    
}
