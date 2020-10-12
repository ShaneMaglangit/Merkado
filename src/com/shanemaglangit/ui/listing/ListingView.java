package com.shanemaglangit.ui.listing;

import com.shanemaglangit.components.HintTextField;
import com.shanemaglangit.components.ProductList;
import com.shanemaglangit.config.Config;
import com.shanemaglangit.res.Resources;
import com.shanemaglangit.util.Util;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;

public class ListingView extends JFrame {
    private JLayeredPane paneMain;
    private JPanel pnlDefault;
    private JPanel pnlOverlay;

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
    private JScrollPane productScrollPane;
    private ProductList productList;

    // Cart Components
    private JPanel pnlFiller;
    private JPanel pnlCart;

    /**
     * Constructor where all of the components of the frame are created
     * @throws HeadlessException
     */
    public ListingView() throws HeadlessException {
        GridLayout layoutPrice;

        // Set the frame preferences
        this.setTitle(Config.TITLE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setMinimumSize(new Dimension(1200, (int) (Config.WINDOW_HEIGHT * 0.90)));
        this.setMaximumSize(new Dimension(Config.WINDOW_WIDTH, Config.WINDOW_HEIGHT));
        this.getContentPane().setLayout(new GridLayout(1, 1));
        this.getContentPane().setBackground(Color.WHITE);

        // Set the frame logo
        this.setIconImage(Util.createImageIcon(this, "../.." +  Resources.LOGO_PATH).getImage());

        // Create the layered pane
        paneMain = new JLayeredPane();
        paneMain.addComponentListener(new ComponentListener() {
            @Override
            public void componentResized(ComponentEvent e) {
                for(Component component : paneMain.getComponents()) {
                    component.setSize(paneMain.getSize());
                }
            }

            @Override public void componentMoved(ComponentEvent e) { }
            @Override public void componentShown(ComponentEvent e) {}
            @Override public void componentHidden(ComponentEvent e) {}
        });
        this.getContentPane().add(paneMain);

        // Add default panel
        pnlDefault = new JPanel();
        pnlDefault.setLayout(new BoxLayout(pnlDefault, BoxLayout.Y_AXIS));
        paneMain.add(pnlDefault, JLayeredPane.DEFAULT_LAYER);

        // Create the header panel
        pnlHeader = new JPanel();
        pnlHeader.setAlignmentX(Component.CENTER_ALIGNMENT);
        pnlHeader.setBackground(Resources.PRIMARY);
        pnlHeader.setLayout(new BoxLayout(pnlHeader, BoxLayout.X_AXIS));
        pnlHeader.setBorder(new LineBorder(Resources.PRIMARY, 6));
        pnlHeader.setMaximumSize(new Dimension(Config.WINDOW_WIDTH, 50));
        pnlDefault.add(pnlHeader, JLayeredPane.DEFAULT_LAYER);

        pnlHeader.add(Box.createRigidArea(new Dimension(16, 0)));

        // Create the logo component
        lblLogo = new JLabel(Util.createImageIcon(this, "../.." +  Resources.LOGO_LIGHT_EXPANDED_PATH));
        pnlHeader.add(lblLogo);

        pnlHeader.add(Box.createRigidArea(new Dimension(16, 0)));

        // Create the search bar
        txtSearch = new HintTextField("What item are you looking for?");
        txtSearch.setAlignmentY(Component.CENTER_ALIGNMENT);
        txtSearch.setFont(Resources.createPoppinsFont(Resources.FontWeight.PLAIN, 12));
        txtSearch.setMargin(new Insets(6, 6, 6, 6));
        txtSearch.setHasBorder(false);
        pnlHeader.add(txtSearch);

        pnlHeader.add(Box.createRigidArea(new Dimension(16, 0)));

        // Create the cart button
        lblCart = new JLabel(Util.createImageIcon(this, "../.." +  Resources.IC_CART));
        pnlHeader.add(lblCart);

        pnlHeader.add(Box.createRigidArea(new Dimension(16, 0)));

        // Create the content panel
        pnlContents = new JPanel();
        pnlContents.setAlignmentX(Component.CENTER_ALIGNMENT);
        pnlContents.setLayout(new BoxLayout(pnlContents, BoxLayout.LINE_AXIS));
        pnlDefault.add(pnlContents, JLayeredPane.DEFAULT_LAYER);

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
        productList = new ProductList(5, 4);
        productList.setMaximumSize(new Dimension(Config.WINDOW_WIDTH, Config.WINDOW_HEIGHT));

        // Create the scroll pane
        productScrollPane = new JScrollPane(productList);
        productScrollPane.setAlignmentX(Component.CENTER_ALIGNMENT);
        productScrollPane.setBorder(new EmptyBorder(6, 6, 6, 6));
        productScrollPane.setMaximumSize(new Dimension(Config.WINDOW_WIDTH, Config.WINDOW_HEIGHT));
        pnlListing.add(productScrollPane);

        // Add the overlay panel
        pnlOverlay = new JPanel();
        pnlOverlay.addMouseListener(new MouseAdapter() {});
        pnlOverlay.setBackground(null);
        pnlOverlay.setOpaque(false);
        pnlOverlay.setVisible(false);
        pnlOverlay.setLayout(new BoxLayout(pnlOverlay, BoxLayout.X_AXIS));
        paneMain.add(pnlOverlay, JLayeredPane.MODAL_LAYER);

        // Create the cart overlay components
        pnlFiller = new JPanel();
        pnlFiller.setOpaque(false);
        pnlFiller.setMaximumSize(new Dimension(Config.WINDOW_WIDTH, Config.WINDOW_HEIGHT));
        pnlFiller.setAlignmentX(Component.CENTER_ALIGNMENT);
        pnlOverlay.add(pnlFiller);

        pnlCart = new JPanel();
        pnlCart.setBackground(Resources.PRIMARY);
        pnlCart.setMaximumSize(new Dimension(400, Config.WINDOW_HEIGHT));
        pnlCart.setAlignmentX(Component.CENTER_ALIGNMENT);
        pnlOverlay.add(pnlCart);
    }

    public ProductList getProductList() {
        return productList;
    }

    public JLabel getLblCart() {
        return lblCart;
    }

    public JPanel getPnlOverlay() {
        return pnlOverlay;
    }

    public JPanel getPnlFiller() {
        return pnlFiller;
    }
}
