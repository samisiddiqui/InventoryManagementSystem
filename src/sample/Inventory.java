package sample;

import javafx.collections.ObservableList;

public class Inventory {
    private ObservableList<Part> allParts;
    private ObservableList<Product> allProducts;

    public void addPart(Part newPart) {
        allParts.add(newPart);
    }

    /*public static void addProduct(Product newProduct) {
        allProducts.add(newProduct);
    }

    public static Part lookupPart(int partId) {
        Part x = null;
        for (Part z : allParts) {
            if (z.getId() == partId) {
                x = z;
            }
        }
        return x;
    }

    public static Product lookupProduct(int productId) {
        Product x = null;
        for (Product z : allProducts) {
            if (z.getId() == productId) {
                x = z;
            }
        }
        return x;
    }

    public static ObservableList<Part> lookupPart(String partName) {
        ObservableList<Part> x = null;
        for (Part z : allParts) {
            if (z.getName().contains(partName)) {
                x.add(z);
            }
        }
        return x;
    }

    public static ObservableList<Product> lookupProduct(String productName) {
        ObservableList<Product> x = null;
        for (Product z : allProducts) {
            if (z.getName().contains(productName)) {
                x.add(z);
            }
        }
        return x;
    }

    public static void updatePart(int index, Part selectedPart) {
        for (Part z : allParts) {
            if (z.getId() == index) {
                z = selectedPart;
            }
        }
    }

    public static void updateProduct(int index, Product selectedProduct) {
        for (Product z : allProducts) {
            if (z.getId() == index) {
                z = selectedProduct;
            }
        }
    }

    public static boolean deletePart(Part selectedPart) {
        for (Part z : allParts) {
            if (z.equals(selectedPart)) {
                allParts.remove(z);
            }
            return true;
        }
        return false;
    }

    public static boolean deleteProduct(Product selectedProduct) {
        for (Product z : allProducts) {
            if (z.equals(selectedProduct)) {
                if (selectedProduct.getAllAssociatedParts() != null) {
                    return false;
                }
                else {
                    allProducts.remove(selectedProduct);
                    return true;
                }
            }
        }
        return false;
    }

    public static ObservableList<Part> getAllParts() {
        return allParts;
    }

    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }*/
}
