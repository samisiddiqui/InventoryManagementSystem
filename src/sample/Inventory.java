package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * @author Sami Siddiqui
 */
public class Inventory {
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    /**
     *
     * @param newPart adds newPart to allParts
     */
    public static void addPart(Part newPart) {
        allParts.add(newPart);
        System.out.println("Part ID " + newPart.getId() + " added");
    }

    /**
     *
     * @param newProduct adds newProduct to allProducts
     */
    public static void addProduct(Product newProduct) {
        allProducts.add(newProduct);
        System.out.println("Product ID " + newProduct.getId() + " added");
    }

    /**
     *
     * @param partId the partId to search for
     * @return Part whose ID matches partID
     */
    public static Part lookupPart(int partId) {
        Part x = null;
        for (Part z : allParts) {
            if (z.getId() == partId) {
                x = z;
            }
        }
        return x;
    }

    /**
     *
     * @param productId the productId to search for
     * @return Product whose ID matches productID
     */
    public static Product lookupProduct(int productId) {
        Product x = null;
        for (Product z : allProducts) {
            if (z.getId() == productId) {
                x = z;
            }
        }
        return x;
    }

    /**
     *
     * @param partName String to search for
     * @return ObservableList of all Parts whose name contains partName
     */
    public static ObservableList<Part> lookupPart(String partName) {
        ObservableList<Part> x = FXCollections.observableArrayList();
        for (Part z : allParts) {
            if (z.getName().contains(partName)) {
                System.out.println(z.getName());
                x.add(z);
            }
        }
        return x;
    }

    /**
     *
     * @param productName String to search for
     * @return ObservableList of all Products whose name contains productName
     */
    public static ObservableList<Product> lookupProduct(String productName) {
        ObservableList<Product> x = FXCollections.observableArrayList();
        for (Product z : allProducts) {
            if (z.getName().contains(productName)) {
                x.add(z);
            }
        }
        return x;
    }

    /**
     *
     * @param index the index of part to update
     * @param selectedPart the new part to replace part whose ID is index
     */
    public static void updatePart(int index, Part selectedPart) {
        for (Part z : allParts) {
            if (z.getId() == index) {
                allParts.remove(z);
                break;
            }
        }
        allParts.add(selectedPart);
        System.out.println("Part ID " + index + " updated");
    }

    /**
     *
     * @param index the index of product to update
     * @param selectedProduct the new product to replace product whose ID is index
     */
    public static void updateProduct(int index, Product selectedProduct) {
        for (Product z : allProducts) {
            if (z.getId() == index) {
                allProducts.remove(z);
                break;
            }
        }
        allProducts.add(selectedProduct);
        System.out.println("Product ID " + index + " updated");
    }

    /**
     *
     * @param selectedPart the part to delete
     * @return true if part was deleted, otherwise false
     */
    public static boolean deletePart(Part selectedPart) {
        for (Part z : allParts) {
            if (z.getId() == selectedPart.getId()) {
                allParts.remove(z);
                System.out.println("Part ID " + selectedPart.getId() + " deleted");
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @param selectedProduct the product to delete
     * @return true if product was deleted, otherwise false
     */
    public static boolean deleteProduct(Product selectedProduct) {
        for (Product z : allProducts) {
            if (z.getId() == selectedProduct.getId()) {
                if (selectedProduct.getAllAssociatedParts().size() != 0) {
                    System.out.println("Cannot delete, product contains associated parts");
                    return false;
                }
                else {
                    allProducts.remove(z);
                    System.out.println("Product ID " + z.getId() + " deleted");
                    return true;
                }
            }
        }
        return false;
    }

    /**
     *
     * @return ObservableList of all parts in inventory
     */
    public static ObservableList<Part> getAllParts() {
        return allParts;
    }

    /**
     *
     * @return ObservableList of all products in inventory
     */
    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }
}
