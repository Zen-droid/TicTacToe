package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame implements ActionListener {
    private JButton[][] grid = new JButton[3][3];
    private int turn = 0;

    public static void main(String[] args) {
        new Main() .setVisible(true);
    }

    private Main() {
        super("Tic Tac Toe");
        setSize(350,  350);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 3));

        JMenuBar bar = new JMenuBar();
        JButton reset = new JButton("RESET");
        JButton exit = new JButton("EXIT");

        reset.setActionCommand("reset");
        exit.setActionCommand("exit");

        reset.addActionListener(this);
        exit.addActionListener(this);

        bar.add(reset);
        bar.add(exit);
        setJMenuBar(bar);
        InputGrid(grid);
    }

    // Inserting The Buttons
    private void InputGrid(JButton[][] grid) {
        int counter=1;
        for (int i=0; i < 3; i++){
            for (int j=0; j < 3; j++){
                grid[i][j] = new JButton();
                add(grid[i][j]);
                grid[i][j].setActionCommand(String.valueOf(counter));
                grid[i][j].addActionListener(this);
                counter++;
            }
        }
    }

    // Check for Button Press
    @Override
    public void actionPerformed(ActionEvent e) {
        String name = e.getActionCommand();

        switch (name) {
            case "1":
                CreateLabel(0, 0);
                break;
            case "2":
                CreateLabel(0, 1);
                break;
            case "3":
                CreateLabel(0, 2);
                break;
            case "4":
                CreateLabel(1, 0);
                break;
            case "5":
                CreateLabel(1, 1);
                break;
            case "6":
                CreateLabel(1, 2);
                break;
            case "7":
                CreateLabel(2, 0);
                break;
            case "8":
                CreateLabel(2, 1);
                break;
            case "9":
                CreateLabel(2, 2);
                break;
            case "reset":
                turn = 0;
                ResetGame();
                break;
            case "exit":
                System.out.println("Closed");
                System.exit(0);
        }
    }

    // Label Creating and Win Check
    private void CreateLabel(int x, int y) {
        String label = grid[x][y].getText();
        String letter;

        if (label.equals("")) {
            if (turn % 2 == 0) {
                letter = "X";
            } else {
                letter = "O";
            }
            grid[x][y].setText(letter);
            grid[x][y].setFont(new Font("Arial", Font.BOLD, 40));
            turn++;

            if (CheckHorizontalWin(letter)
                || CheckVerticalWin(letter)
                || CheckDiagonalToLeft(letter)
                || CheckDiagonalToRight(letter)
            ){
                JOptionPane.showMessageDialog(null, letter + " has won!");
                ResetGame();
            }
            else if (turn >= 9){
                JOptionPane.showMessageDialog(null, "DRAW!");
                ResetGame();
            }
        }
    }

    // Resets the Game
    private void ResetGame(){
        turn=0;
        for (int i=0; i < 3; i++){
            for (int j=0; j < 3; j++){
                grid[i][j].setText("");
            }
        }
    }

    public boolean CheckHorizontalWin(String letter){
        for (int y=0; y < 3; y++){
            if (grid[y][0].getText().equals(letter)
                && grid[y][1].getText().equals(letter)
                && grid[y][2].getText().equals(letter)
            ){
                return true;
            }
        }
        return false;
    }

    public boolean CheckVerticalWin(String letter){
        for (int x=0; x < 3; x++){
            if (grid[0][x].getText().equals(letter)
                    && grid[1][x].getText().equals(letter)
                    && grid[2][x].getText().equals(letter)
            ){
                return true;
            }
        }
        return false;
    }

    public boolean CheckDiagonalToRight(String letter){
        return grid[2][0].getText().equals(letter)
            && grid[1][1].getText().equals(letter)
            && grid[0][2].getText().equals(letter);
    }

    public boolean CheckDiagonalToLeft(String letter){
        return grid[0][0].getText().equals(letter)
            && grid[1][1].getText().equals(letter)
            && grid[2][2].getText().equals(letter);
    }

}
