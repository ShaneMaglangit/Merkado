package com.shanemaglangit.ui.listing;

import com.shanemaglangit.components.HintTextField;
import com.shanemaglangit.components.ProductList;
import com.shanemaglangit.config.Config;
import com.shanemaglangit.data.Product;
import com.shanemaglangit.res.Resources;
import com.shanemaglangit.util.ItemOverflowException;
import com.shanemaglangit.util.Util;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.util.ArrayList;

public class ListingView extends JFrame {

    // Header components
    private JPanel pnlHeader;
    private JLabel lblLogo;
    private HintTextField txtSearch;
    private JLabel lblCart;

    // Content components
    private JPanel pnlContents;

    // Filter components
    private JPanel pnlFilter;
    private JLabel lblMarket;
    private JComboBox<ArrayList<String>> cbxMarket;
    private JLabel lblCategory;
    private JComboBox<ArrayList<String>> cbxCategory;
    private JLabel lblPriceRange;
    private JPanel pnlPriceRange;
    private HintTextField txtPriceMin;
    private HintTextField txtPriceMax;
    private JLabel lblSort;
    private ButtonGroup bgSort;
    private JRadioButton rbtnLowToHigh;
    private JRadioButton rbtnHighToLow;

    // Listing components
    private JPanel pnlListing;
    private ProductList productList;

    /**
     * Constructor where all of the components of the frame are created
     * @throws HeadlessException
     */
    public ListingView() throws HeadlessException {
        GridLayout layoutPrice;

        // Set the frame preferences
        this.setTitle(Config.TITLE);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setMinimumSize(new Dimension(1200, (int) (Config.WINDOW_HEIGHT * 0.90)));
        this.setMaximumSize(new Dimension(Config.WINDOW_WIDTH, Config.WINDOW_HEIGHT));
        this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

        // Set the frame logo
        this.setIconImage(Util.createImageIcon(this, "../.." +  Resources.LOGO_PATH).getImage());

        // Create the header panel
        pnlHeader = new JPanel();
        pnlHeader.setAlignmentX(Component.CENTER_ALIGNMENT);
        pnlHeader.setBackground(Resources.PRIMARY);
        pnlHeader.setLayout(new BoxLayout(pnlHeader, BoxLayout.X_AXIS));
        pnlHeader.setBorder(new LineBorder(Resources.PRIMARY, 6));
        pnlHeader.setMaximumSize(new Dimension(Config.WINDOW_WIDTH, 50));
        this.getContentPane().add(pnlHeader);

        // Create the logo component
        lblLogo = new JLabel(Util.createImageIcon(this, "../.." +  Resources.LOGO_LIGHT_EXPANDED_PATH));
        pnlHeader.add(lblLogo);

        pnlHeader.add(Box.createRigidArea(new Dimension(12, 0)));

        // Create the search bar
        txtSearch = new HintTextField("What item are you looking for?");
        txtSearch.setAlignmentY(Component.CENTER_ALIGNMENT);
        txtSearch.setFont(Resources.createPoppinsFont(Resources.FontWeight.PLAIN, 12));
        txtSearch.setMargin(new Insets(6, 6, 6, 6));
        txtSearch.setHasBorder(false);
        pnlHeader.add(txtSearch);

        pnlHeader.add(Box.createRigidArea(new Dimension(12, 0)));

        // Create the cart button
        lblCart = new JLabel(Util.createImageIcon(this, "../.." +  Resources.IC_CART));
        pnlHeader.add(lblCart);

        // Create the content panel
        pnlContents = new JPanel();
        pnlContents.setAlignmentX(Component.CENTER_ALIGNMENT);
        pnlContents.setLayout(new BoxLayout(pnlContents, BoxLayout.LINE_AXIS));
        this.getContentPane().add(pnlContents);

        // Create the filter panel
        pnlFilter = new JPanel();
        pnlFilter.setAlignmentY(Component.TOP_ALIGNMENT);
        pnlFilter.setAlignmentX(Component.LEFT_ALIGNMENT);
        pnlFilter.setMaximumSize(new Dimension(200, Config.WINDOW_HEIGHT));
        pnlFilter.setLayout(new BoxLayout(pnlFilter, BoxLayout.Y_AXIS));
        pnlFilter.setBorder(new EmptyBorder(12, 12, 12, 12));
        pnlContents.add(pnlFilter);

        // Crate the market filter
        lblMarket = new JLabel("Market");
        lblMarket.setAlignmentX(Component.LEFT_ALIGNMENT);
        lblMarket.setFont(Resources.createPoppinsFont(Resources.FontWeight.BOLD, 12));
        pnlFilter.add(lblMarket);

        cbxMarket = new JComboBox<ArrayList<String>>();
        cbxMarket.setAlignmentX(Component.LEFT_ALIGNMENT);
        cbxMarket.setMaximumSize(new Dimension(Config.WINDOW_WIDTH, 25));
        pnlFilter.add(cbxMarket);

        pnlFilter.add(Box.createRigidArea(new Dimension(0, 16)));

        // Create the category filter
        lblCategory = new JLabel("Category");
        lblCategory.setAlignmentX(Component.LEFT_ALIGNMENT);
        lblCategory.setFont(Resources.createPoppinsFont(Resources.FontWeight.BOLD, 12));
        pnlFilter.add(lblCategory);

        cbxCategory = new JComboBox<ArrayList<String>>();
        cbxCategory.setAlignmentX(Component.LEFT_ALIGNMENT);
        cbxCategory.setMaximumSize(new Dimension(Config.WINDOW_WIDTH, 25));
        pnlFilter.add(cbxCategory);

        pnlFilter.add(Box.createRigidArea(new Dimension(0, 16)));

        // Create the price range filter
        lblPriceRange = new JLabel("Price Range");
        lblPriceRange.setAlignmentX(Component.LEFT_ALIGNMENT);
        lblPriceRange.setFont(Resources.createPoppinsFont(Resources.FontWeight.BOLD, 12));
        pnlFilter.add(lblPriceRange);

        layoutPrice = new GridLayout(1, 2);
        layoutPrice.setHgap(6);

        pnlPriceRange = new JPanel();
        pnlPriceRange.setAlignmentX(Component.LEFT_ALIGNMENT);
        pnlPriceRange.setLayout(layoutPrice);
        pnlPriceRange.setMaximumSize(new Dimension(Config.WINDOW_WIDTH, 25));
        pnlFilter.add(pnlPriceRange);

        txtPriceMin = new HintTextField("Min");
        txtPriceMin.setFont(Resources.createPoppinsFont(Resources.FontWeight.PLAIN, 12));
        txtPriceMin.setMargin(new Insets(6, 6, 6, 6));
        pnlPriceRange.add(txtPriceMin);

        txtPriceMax = new HintTextField("Max");
        txtPriceMax.setFont(Resources.createPoppinsFont(Resources.FontWeight.PLAIN, 12));
        txtPriceMax.setMargin(new Insets(6, 6, 6, 6));
        pnlPriceRange.add(txtPriceMax);

        pnlFilter.add(Box.createRigidArea(new Dimension(0, 16)));

        // Create the sorting options
        lblSort = new JLabel("Sort by");
        lblSort.setAlignmentX(Component.LEFT_ALIGNMENT);
        lblSort.setFont(Resources.createPoppinsFont(Resources.FontWeight.BOLD, 12));
        pnlFilter.add(lblSort);

        rbtnLowToHigh = new JRadioButton("Low to high");
        rbtnLowToHigh.setAlignmentX(Component.LEFT_ALIGNMENT);
        rbtnLowToHigh.setFont(Resources.createPoppinsFont(Resources.FontWeight.PLAIN, 12));
        rbtnLowToHigh.setFocusPainted(false);
        rbtnLowToHigh.setIcon(Util.createImageIcon(this, "../.." +  Resources.RADIO_DEFAULT));
        rbtnLowToHigh.setSelectedIcon(Util.createImageIcon(this, "../.." +  Resources.RADIO_SELECTED));
        rbtnLowToHigh.setSelected(true);
        pnlFilter.add(rbtnLowToHigh);

        rbtnHighToLow = new JRadioButton("High to low");
        rbtnHighToLow.setAlignmentX(Component.LEFT_ALIGNMENT);
        rbtnHighToLow.setFont(Resources.createPoppinsFont(Resources.FontWeight.PLAIN, 12));
        rbtnHighToLow.setFocusPainted(false);
        rbtnHighToLow.setIcon(Util.createImageIcon(this, "../.." +  Resources.RADIO_DEFAULT));
        rbtnHighToLow.setSelectedIcon(Util.createImageIcon(this, "../.." +  Resources.RADIO_SELECTED));
        pnlFilter.add(rbtnHighToLow);

        bgSort = new ButtonGroup();
        bgSort.add(rbtnHighToLow);
        bgSort.add(rbtnLowToHigh);

        // Create the listing panel
        pnlListing = new JPanel();
        pnlListing.setLayout(new BoxLayout(pnlListing, BoxLayout.Y_AXIS));
        pnlListing.setAlignmentY(Component.TOP_ALIGNMENT);
        pnlListing.setAlignmentX(Component.RIGHT_ALIGNMENT);
        pnlContents.add(pnlListing);

        // Add the product lists
        productList = new ProductList(2, 4);
        productList.setAlignmentX(Component.CENTER_ALIGNMENT);
        productList.setBorder(new EmptyBorder(6, 6, 6, 6));
        productList.setMaximumSize(new Dimension(Config.WINDOW_WIDTH, Config.WINDOW_HEIGHT));
        pnlListing.add(productList);
    }

    public ProductList getProductList() {
        return productList;
    }
}
