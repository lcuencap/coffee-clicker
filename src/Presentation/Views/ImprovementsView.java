package Presentation.Views;

import Presentation.Fonts.MinecraftFont;

import javax.swing.*;
import javax.swing.border.StrokeBorder;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionListener;

    /**
     * Removes all improvements from the view.
     */
    public void removeImprovements() {
        this.mainPanel.removeAll();

        JPanel improvementsPanel = new JPanel(new GridLayout(1, 4));
        improvementsPanel.setBackground(Color.BLACK);
        JPanel emptyPanel = new JPanel();
        emptyPanel.setOpaque(false);
        improvementsPanel.add(emptyPanel);
        improvementsPanel.add(new JLabel("Name"));
        improvementsPanel.add(new JLabel("Price"));
        improvementsPanel.add(new JLabel("Amount"));
        mainPanel.add(improvementsPanel);

        scrollPane.revalidate();
    }

    /**
     * Updates the table with the specified quantities.
     *
     * @param quantities The quantities to update the table with.
     */
    public void updateTable(int[] quantities) {
        for (int i = 0; i < quantities.length; i++) {
            improvementsTable.setValueAt(quantities[i], i, 1);
        }
    }
}
