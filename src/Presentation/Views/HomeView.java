package Presentation.Views;

import Presentation.Fonts.MinecraftFont;
import Presentation.JImagePanel;
import Presentation.JTexturedButton;
import Presentation.R;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Map;

/**
 * Home screen for creating or resuming games.
 */
public class HomeView extends JPanel implements MyView {
    private final ActionListener listener;
    private JTexturedButton newGame;
    private JTexturedButton resumeGame;
    private JLayeredPane layeredPane;

    public HomeView(ActionListener listener) {
        this.listener = listener;
        initComponents();
        setupBackground();
        setupLayout();
        start();
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        newGame = createMenuButton("New Game", "newGame");
        resumeGame = createMenuButton("Resume Game", "resumeGame");
    }

    private JTexturedButton createMenuButton(String text, String actionCommand) {
        JTexturedButton button = new JTexturedButton(R.BUTTON_DEFAULT, R.BUTTON_PRESSED);
        button.setFont(MinecraftFont.getFont());
        button.setText(text);
        button.setActionCommand(actionCommand);
        return button;
    }

    private void setupBackground() {
        try {
            JImagePanel background = new JImagePanel(R.MAIN_BACKGROUND);
            JImagePanel formBackground = new JImagePanel(R.FORM_BACKGROUND);

            background.setOpaque(false);
            background.setResolution(JImagePanel.EXTEND_RES_WIDTH);
            formBackground.setOpaque(false);

            layeredPane = new JLayeredPane();
            layeredPane.setLayout(new OverlayLayout(layeredPane));

            layeredPane.setLayer(background, 0);
            layeredPane.add(background);

            layeredPane.setLayer(formBackground, 1);
            layeredPane.add(formBackground);
        } catch (IOException e) {
            throw new IllegalStateException("Unable to initialize home view resources", e);
        }
    }

    private void setupLayout() {
        JPanel buttonPanel = new JPanel(new GridLayout(2, 1, 0, 50));
        buttonPanel.setPreferredSize(new Dimension(200, 150));
        buttonPanel.setOpaque(false);
        buttonPanel.add(newGame);
        buttonPanel.add(resumeGame);

        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setOpaque(false);
        centerPanel.add(buttonPanel);

        layeredPane.setLayer(centerPanel, 2);
        layeredPane.add(centerPanel);

        add(layeredPane, BorderLayout.CENTER);
    }

    public void displayGames(Map<Integer, Integer> games) {
        JDialog resumeDialog = new JDialog(
                JOptionPane.getFrameForComponent(this),
                "Resume Game",
                true
        );
        resumeDialog.setSize(300, 400);
        resumeDialog.setLocationRelativeTo(this);

        JPanel gameListPanel = new JPanel();
        gameListPanel.setLayout(new BoxLayout(gameListPanel, BoxLayout.Y_AXIS));

        JScrollPane scrollPane = new JScrollPane(gameListPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        int displayIndex = 1;
        for (Map.Entry<Integer, Integer> entry : games.entrySet()) {
            JTexturedButton gameButton = new JTexturedButton(R.BUTTON_DEFAULT, R.BUTTON_PRESSED);
            gameButton.setText("Game " + displayIndex + " | " + entry.getValue() + " credits");
            gameButton.setAlignmentX(Component.CENTER_ALIGNMENT);

            int gameId = entry.getKey();
            gameButton.addActionListener(event -> {
                resumeDialog.dispose();
                listener.actionPerformed(new ActionEvent(
                        this,
                        ActionEvent.ACTION_PERFORMED,
                        "loadGame-" + gameId
                ));
            });

            gameListPanel.add(gameButton);
            displayIndex++;
        }

        resumeDialog.add(scrollPane);
        resumeDialog.setVisible(true);
    }

    @Override
    public void start() {
        addListenerIfMissing(newGame);
        addListenerIfMissing(resumeGame);
    }

    private void addListenerIfMissing(AbstractButton button) {
        for (ActionListener registered : button.getActionListeners()) {
            if (registered == listener) {
                return;
            }
        }
        button.addActionListener(listener);
    }

    @Override
    public void stop() {
        newGame.removeActionListener(listener);
        resumeGame.removeActionListener(listener);
    }

    @Override
    public void clear() {
        // No transient state to clear.
    }
}
