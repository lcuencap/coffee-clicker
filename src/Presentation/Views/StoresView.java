package Presentation.Views;

import Presentation.Fonts.MinecraftFont;
import Presentation.JImagePanel;
import Presentation.JTexturedButton;
import Presentation.R;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Store view containing generator and upgrade panels.
 */
public class StoresView extends JPanel implements MyView {
    private final ActionListener listener;
    private final CardLayout cardLayout;
    private final JPanel storePanel;
    private final GeneratorsView generatorsView;
    private final ImprovementsView improvementsView;
    private final JImagePanel background;

    public StoresView(ActionListener listener) throws IOException {
        this.listener = listener;

        setSize(800, 600);
        setLayout(new OverlayLayout(this));
        setOpaque(false);

        cardLayout = new CardLayout();

        background = new JImagePanel(R.STORES_BACKGROUND);
        background.setResolution(JImagePanel.EXTEND_RES_WIDTH);

        generatorsView = new GeneratorsView(listener, (ListSelectionListener) listener);
        improvementsView = new ImprovementsView(listener, (ListSelectionListener) listener);

        storePanel = new JPanel(cardLayout);
        storePanel.setBackground(Color.BLACK);

        mount();
    }

    private JTexturedButton createNavigationButton(String text, String actionCommand) {
        JTexturedButton button = new JTexturedButton(R.BUTTON_DEFAULT, R.BUTTON_PRESSED);
        button.setText(text);
        button.setFont(MinecraftFont.getFont());
        button.setActionCommand(actionCommand);
        button.addActionListener(listener);
        return button;
    }

    private void mount() {
        JTexturedButton generatorsButton = createNavigationButton("Generators", "generators");
        JTexturedButton upgradesButton = createNavigationButton("Upgrades", "upgrades");

        JPanel buttonsPanel = new JPanel(new FlowLayout());
        buttonsPanel.setOpaque(false);
        buttonsPanel.add(generatorsButton);
        buttonsPanel.add(upgradesButton);

        storePanel.add("generators", generatorsView);
        storePanel.add("upgrades", improvementsView);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(Color.BLACK);
        contentPanel.setPreferredSize(new Dimension(275, 450));
        contentPanel.add(buttonsPanel);
        contentPanel.add(storePanel);

        JPanel mainPanel = new JPanel(new FlowLayout());
        mainPanel.setOpaque(false);
        mainPanel.setBorder(new EmptyBorder(25, 5, 0, 0));
        mainPanel.add(contentPanel);

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setLayout(new OverlayLayout(layeredPane));
        layeredPane.setLayer(background, 0);
        layeredPane.setLayer(mainPanel, 1);
        layeredPane.add(background);
        layeredPane.add(mainPanel);

        add(layeredPane);
    }

    public void swapPanel(String name) {
        cardLayout.show(storePanel, name);
    }

    public void initialize(
            int basicGenerator,
            int midGenerator,
            int highGenerator,
            int lvlBasicImp,
            int lvlMidImp,
            int lvlHighImp
    ) {
        generatorsView.removeGenerators();
        generatorsView.addGenerator(R.REDBULL, "Redbull", "100", String.valueOf(basicGenerator), GeneratorsView.basicGenDesc);
        generatorsView.addGenerator(R.ENCHANTED_BOOK, "Notes", "200", String.valueOf(midGenerator), GeneratorsView.midGenDesc);
        generatorsView.addGenerator(R.CEUS, "CEUS", "300", String.valueOf(highGenerator), GeneratorsView.highGenDesc);

        improvementsView.addImprovement(R.PILLS, "Pills", "100", String.valueOf(lvlBasicImp), ImprovementsView.basicImprovementDesc);
        improvementsView.addImprovement(R.GLASSES, "Glasses", "200", String.valueOf(lvlMidImp), ImprovementsView.midImprovementDesc);
        improvementsView.addImprovement(R.CARLOS, "Carlos", "300", String.valueOf(lvlHighImp), ImprovementsView.highImprovementDesc);
    }

    @Override
    public void start() {
        // Child views own their listeners.
    }

    @Override
    public void stop() {
        // Child views own their listeners.
    }

    @Override
    public void clear() {
        // No transient state to clear.
    }

    public void updateGeneratorsView(
            int[] quantities,
            float[] totalCreditsPerSecond,
            float[] globalProductionPercentages,
            int[] prices
    ) {
        generatorsView.updateTable(quantities, totalCreditsPerSecond, globalProductionPercentages, prices);
    }

    public void updateImprovementsView(int[] quantities) {
        improvementsView.updateTable(quantities);
    }

    public void noGenerators(String improvement) {
        JOptionPane.showMessageDialog(
                this,
                "You don't have generators to apply the improvement " + improvement + " to.",
                "No Generators Error",
                JOptionPane.ERROR_MESSAGE
        );
    }

    public void noEnoughMoney(int gameCurrency) {
        JOptionPane.showMessageDialog(
                this,
                "You don't have enough money for that. You have " + gameCurrency + ".",
                "No Money Error",
                JOptionPane.ERROR_MESSAGE
        );
    }
}
