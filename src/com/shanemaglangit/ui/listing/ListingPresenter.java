package com.shanemaglangit.ui.listing;

import com.shanemaglangit.config.Config;
import com.shanemaglangit.data.Order;
import com.shanemaglangit.data.PagedLinkedList;
import com.shanemaglangit.data.Product;
import com.shanemaglangit.data.SinglyLinkedList;
import com.shanemaglangit.navigation.Navigation;
import com.shanemaglangit.repository.Repository;
import com.shanemaglangit.res.Resources;
import com.shanemaglangit.util.Util;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.logging.Level;

public class ListingPresenter {
    private Repository repository;
    private PagedLinkedList<Product> productList;
    private ListingView view;

    /**
     * Constructor
     * @param view view attached to this controller
     */
    public ListingPresenter(ListingView view) {
        this.view = view;
        this.repository = Repository.getInstance();
        this.productList = repository.getProductList();
        loadProducts();
        loadCategories();
        loadMarkets();
        attachListeners();
        showView();
    }

    /**
     * Shows the view
     */
    private void showView() {
        view.setLocationRelativeTo(null);
        view.setVisible(true);
    }

    /**
     * Attach listeners to the view components
     */
    private void attachListeners() {
        view.getBtnCart().addActionListener(e -> toggleCartVisibility());
        view.getBtnClose().addActionListener(e -> toggleCartVisibility());
        view.getPnlFiller().addMouseListener(new MouseListener() {
            @Override public void mouseClicked(MouseEvent e) { toggleCartVisibility(); }
            @Override public void mousePressed(MouseEvent e) { }
            @Override public void mouseReleased(MouseEvent e) { }
            @Override public void mouseEntered(MouseEvent e) { }
            @Override public void mouseExited(MouseEvent e) { }
        });
        view.getBtnCheckout().addActionListener(e -> {
            Navigation.checkout(view);
            toggleCartVisibility();
        });
        view.getProductList().setClickListener(product -> Navigation.addToCart(view, product));
        view.getTxtSearch().addActionListener(e -> loadProducts());
        view.getCbxMarket().addItemListener(e -> loadProducts());
        view.getCbxCategory().addItemListener(e -> loadProducts());
        view.getTxtPriceMin().addActionListener(e -> loadProducts());
        view.getTxtPriceMax().addActionListener(e -> loadProducts());
        view.getRbtnLowToHigh().addActionListener(e -> loadProducts());
        view.getRbtnHighToLow().addActionListener(e -> loadProducts());
        view.getTxtPage().addActionListener(e -> updatePage(0));
        view.getBtnPrev().addActionListener(e -> updatePage(-1));
        view.getBtnNext().addActionListener(e -> updatePage(1));
    }

    /**
     * Used by the listeners to update the page of the products
     * @param incr value that will be added to the current page
     */
    private void updatePage(int incr) {
        // Get the current page and add the increment
        int page = Integer.parseInt(view.getTxtPage().getText().replaceAll(",", ""));
        page += incr;

        // Ensure the page stays within its bounds
        if(page < 1) page = 1;
        else if(page > productList.getPagesCount()) page = productList.getPagesCount();

        // Temporarily disable the pagination controls
        pausePagination();

        // Set the products
        setProducts(page);
    }

    /**
     * Set the product to the product list component
     */
    private void setProducts(int page) {
        // Update the text field for the page number
        view.getTxtPage().setText(String.valueOf(page));
        view.getProductList().setProducts(productList.getPage(page - 1));
    }

    /**
     * Used to load the products given the filter by the user
     */
    private void loadProducts() {
        // Store the filters accordingly
        String search = view.getTxtSearch().getText();
        String market = (String) view.getCbxMarket().getSelectedItem();
        String category = (String) view.getCbxCategory().getSelectedItem();
        int minPrice = Integer.parseInt(view.getTxtPriceMin().getText().replaceAll(",", ""));
        int maxPrice = Integer.parseInt(view.getTxtPriceMax().getText().replaceAll(",", ""));
        boolean isAscending = view.getRbtnLowToHigh().isSelected();

        Util.log(Level.INFO, String.format("Filters:%s,%s,%s,%d,%d,%b", search, market, category, minPrice, maxPrice, isAscending));

        // Use the ProductFilter to filter the products
        this.productList = new ProductFilter(repository.getProductList())
            .title(search)
            .market(market)
            .category(category)
            .minPrice(minPrice)
            .maxPrice(maxPrice)
            .sort(isAscending)
            .get();

        // Reset the page back to 1
        setProducts(1);
    }

    /**
     * Sets the categories for the filter
     */
    private void loadCategories() {
        String[] categoryArr = Util.stringListToArr(repository.getCategoryList());
        ComboBoxModel<String> cbxModel = new DefaultComboBoxModel<>(categoryArr);
        view.getCbxCategory().setModel(cbxModel);
    }

    /**
     * Sets the markets for the filter
     */
    private void loadMarkets() {
        String[] marketArr = Util.stringListToArr(repository.getMarketList());
        ComboBoxModel<String> cbxModel = new DefaultComboBoxModel<>(marketArr);
        view.getCbxMarket().setModel(cbxModel);
    }

    /**
     * Set the order to the order list component
     */
    private void setOrders() {
        SinglyLinkedList<Order> orderList = repository.getOrderList();
        double subTotal = Util.computeOrderSubTotal(orderList);

        view.getOrderList().setOrders(orderList);

        if(subTotal > 0) {
            double total = subTotal + Config.SERVICE_FEE;
            view.getLblSubTotal().setText("PHP " + subTotal);
            view.getLblTotal().setText("PHP " + total);
            view.getLblServiceFee().setText("PHP " + Config.SERVICE_FEE);
            view.getBtnCheckout().setEnabled(true);
            view.getBtnCheckout().setBackground(Resources.PRIMARY);
        } else {
            view.getLblSubTotal().setText("PHP 0.00");
            view.getLblServiceFee().setText("PHP 0.00");
            view.getLblTotal().setText("PHP 0.00");
            view.getBtnCheckout().setEnabled(false);
            view.getBtnCheckout().setBackground(Color.DARK_GRAY);
        }
    }

    /**
     * Toggles the visibility of the cart panel modal / overlay
     */
    private void toggleCartVisibility() {
        JPanel pnlOverlay = view.getPnlOverlay();
        if(!pnlOverlay.isVisible()) setOrders();
        pnlOverlay.setVisible(!pnlOverlay.isVisible());
    }

    /**
     * Temporarily disables the pagination
     */
    private void pausePagination() {
        togglePagination(false);
        SwingUtilities.invokeLater(() -> togglePagination(true));
    }

    /**
     * Used to toggle the pagination views state
     * @param isEnabled state of the views
     */
    private void togglePagination(boolean isEnabled) {
        // Identify the color to be set as the background for the buttons
        Color color;
        if(isEnabled) color = Resources.PRIMARY;
        else color = Color.LIGHT_GRAY;

        // Set the states
        view.getTxtPage().setEnabled(isEnabled);
        view.getBtnNext().setEnabled(isEnabled);
        view.getBtnPrev().setEnabled(isEnabled);

        // Set the background and border colors
        view.getBtnNext().setBackground(color);
        view.getBtnPrev().setBackground(color);
        view.getBtnNext().setBorder(new LineBorder(color, 6));
        view.getBtnPrev().setBorder(new LineBorder(color, 6));
    }
}
