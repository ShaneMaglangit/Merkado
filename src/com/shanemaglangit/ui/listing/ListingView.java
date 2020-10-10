package com.shanemaglangit.ui.listing;

import com.shanemaglangit.components.HintTextField;
import com.shanemaglangit.config.Config;
import com.shanemaglangit.res.Resources;
import com.shanemaglangit.util.Util;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
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
    private HintTextField txtPriceMin;
    private HintTextField txtPriceMax;
    private JLabel lblSort;
    private ButtonGroup bgSort;
    private JRadioButton rbtnLowToHigh;
    private JRadioButton rbtnHighToLow;

    // Listing components
    private JPanel pnlListing;

    /**
     * Constructor where all of the components of the frame are created
     * @throws HeadlessException
     */
    public ListingView() throws HeadlessException {
        // Set the frame preferences
        this.setTitle(Config.TITLE);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setMinimumSize(new Dimension(Config.WINDOW_WIDTH / 3, 0));
        this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

        // Set the frame logo
        this.setIconImage(Util.createImageIcon(this, Resources.LOGO_PATH).getImage());

        // Create the header panel
        pnlHeader = new JPanel();
        pnlHeader.setAlignmentX(Component.CENTER_ALIGNMENT);
        pnlHeader.setBackground(Resources.PRIMARY);
        pnlHeader.setLayout(new BoxLayout(pnlHeader, BoxLayout.X_AXIS));
        pnlHeader.setBorder(new LineBorder(Resources.PRIMARY, 12));
        this.getContentPane().add(pnlHeader);

        // Create the logo component
        lblLogo = new JLabel(Util.createImageIcon(this, Resources.LOGO_LIGHT_EXPANDED_PATH));
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
        lblCart = new JLabel(Util.createImageIcon(this, Resources.IC_CART));
        pnlHeader.add(lblCart);

        // Create the content panel
        pnlContents = new JPanel();
        pnlContents.setAlignmentX(Component.CENTER_ALIGNMENT);
        pnlContents.setLayout(new BoxLayout(pnlContents, BoxLayout.X_AXIS));
        this.getContentPane().add(pnlContents);

        // Create the filter panel
        pnlFilter = new JPanel();
        pnlFilter.setLayout(new BoxLayout(pnlFilter, BoxLayout.Y_AXIS));
        pnlFilter.setBorder(new EmptyBorder(12, 12, 12, 12));
        pnlContents.add(pnlFilter);

        // Crate the market filter
        lblMarket = new JLabel("Market");
        lblMarket.setAlignmentX(Component.LEFT_ALIGNMENT);
        lblMarket.setFont(Resources.createPoppinsFont(Resources.FontWeight.MEDIUM, 12));
        pnlFilter.add(lblMarket);

        cbxMarket = new JComboBox<ArrayList<String>>();
        cbxMarket.setAlignmentX(Component.LEFT_ALIGNMENT);
        pnlFilter.add(cbxMarket);

        // Create the listing panel
        pnlListing = new JPanel();
        pnlContents.add(pnlListing);
    }
}
