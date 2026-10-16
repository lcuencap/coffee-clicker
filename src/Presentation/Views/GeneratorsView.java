package Presentation.Views;

import Presentation.Fonts.MinecraftFont;

import javax.swing.*;
import javax.swing.border.StrokeBorder;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionListener;

    /**
     * Removes all generators from the view.
     */
    public void removeGenerators() {
        this.mainPanel.removeAll();
        JPanel generatorsPanel = new JPanel(new GridLayout(1, 4));
        generatorsPanel.setBackground(Color.BLACK);
        JPanel emptyPanel = new JPanel();
        emptyPanel.setOpaque(false);
        generatorsPanel.add(emptyPanel);
        generatorsPanel.add(new JLabel("Name"));
        generatorsPanel.add(new JLabel("Price"));
        generatorsPanel.add(new JLabel("Amount"));
        mainPanel.add(generatorsPanel);

        scrollPane.revalidate();
    }

    /**
     * Updates the table with new data.
     *
     * @param quantities                 The quantities of each generator.
     * @param totalCreditsPerSecond      The total credits per second of each generator.
     * @param globalProductionPercentages The global production percentages of each generator.
     * @param prices                     The prices of each generator.
     */
    public void updateTable(int[] quantities, float[] totalCreditsPerSecond, float[] globalProductionPercentages, int[] prices) {
        for (int i = 0; i < quantities.length; i++) {
            generatorsTable.setValueAt(quantities[i], i, 1);
            generatorsTable.setValueAt(prices[i], i, 2);
            generatorsTable.setValueAt(String.format("%.2f", totalCreditsPerSecond[i]), i, 3);
            generatorsTable.setValueAt(String.format("%.2f", globalProductionPercentages[i]), i, 4);
        }
    }
}
