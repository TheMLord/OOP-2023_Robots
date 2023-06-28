package gui;

import localization.LocalizedComponent;
import model.robot.ModelRobot;

import javax.swing.*;

import java.awt.*;
import java.util.Observable;
import java.util.Observer;

import static localization.ApplicationLocalizer.applicationLocalizer;

public class PositionWindow extends JInternalFrame implements Observer, LocalizedComponent {
    private final ModelRobot modelRobot;
    private final JLabel xPositionLabel;
    private final JLabel yPositionLabel;

    private static String titleWindow = applicationLocalizer.getLocalizedText("titlePositionWindow");

    public PositionWindow(ModelRobot modelRobot) {
        super(titleWindow, true, true, true, true);
        this.modelRobot = modelRobot;
        modelRobot.addObserver(this);

        xPositionLabel = new JLabel("X : %f".formatted(modelRobot.getRobotPositionX()));
        yPositionLabel = new JLabel("Y : %f".formatted(modelRobot.getRobotPositionY()));

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(xPositionLabel, BorderLayout.CENTER);
        panel.add(yPositionLabel, BorderLayout.AFTER_LAST_LINE);
        getContentPane().add(panel);
        pack();

    }

    @Override
    public void update(Observable o, Object arg) {
        EventQueue.invokeLater(this::updateRobotPosition);
    }

    @Override
    public void updateComponents() {
        titleWindow = applicationLocalizer.getLocalizedText("titlePositionWindow");
        this.setTitle(titleWindow);
    }

    private void updateRobotPosition() {
        xPositionLabel.setText("X : %f".formatted(modelRobot.getRobotPositionX()));
        yPositionLabel.setText("Y : %f".formatted(modelRobot.getRobotPositionY()));

    }
}