/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaassignment.SalesManager;

import java.util.List;

/**
 *
 * @author vroom
 */
public interface ItemsDAO {
    List<Items> viewItems();
    List<Items> searchItemsByCode(String itemCode) throws Exception;
    List<Items> searchItemsByName(String itemName) throws Exception;
    List<Items> getItemsNeedingReorder() throws Exception;
}
