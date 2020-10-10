package com.shanemaglangit.ui.listing;

import com.shanemaglangit.components.HintTextField;
import com.shanemaglangit.config.Config;
import com.shanemaglangit.res.Resources;
import com.shanemaglangit.util.Util;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;

public class ListingView extends JFrame {

    // Header components
    private JPanel pnlHeader;
    private JLabel lblLogo;
    private JTextField txtSearch;
    private JLabel lblCart;

    // Content components
    private JPanel pnlComponents;

    // Filter components
    private JPanel pnlFilter;
    private JLabel lblMarket;
    private JComboBox<ArrayList<String>> cbxMarket;
    private JLabel lblCategory;
    private JComboBox<ArrayList<String>> cbxCategory;
    private JLabel lblPriceRange;
    private JTextField txtPriceMin;
    private JTextField txtPriceMax;
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

        // Create the header components
        pnlHeader = new JPanel();
        pnlHeader.setBackground(Resources.PRIMARY);
        pnlHeader.setLayout(new BoxLayout(pnlHeader, BoxLayout.X_AXIS));
        pnlHeader.setBorder(new LineBorder(Resources.PRIMARY, 8));
        this.getContentPane().add(pnlHeader);

        lblLogo = new JLabel(Util.createImageIcon(this, Resources.LOGO_LIGHT_EXPANDED_PATH));
        pnlHeader.add(lblLogo);

        pnlHeader.add(Box.createRigidArea(new Dimension(12, 0)));

        txtSearch = new HintTextField("What item are you looking for?", 32);
        txtSearch.setFont(Resources.createPoppinsFont(Resources.FontWeight.PLAIN, 12));
        txtSearch.setMargin(new Insets(6, 6, 6, 6));
        pnlHeader.add(txtSearch);

        pnlHeader.add(Box.createRigidArea(new Dimension(12, 0)));

        lblLogo = new JLabel(Util.createImageIcon(this, Resources.IC_CART));
        pnlHeader.add(lblLogo);
    }
}
