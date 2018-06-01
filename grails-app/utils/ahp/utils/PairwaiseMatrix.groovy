package ahp.utils

import Jama.*

class PairwaiseMatrix {
    private String compareTo
    private ArrayList<String> items
    private int row
    private int column
    private Matrix matrix

    PairwaiseMatrix(String compareTo, ArrayList<String> items) {
        this.compareTo = compareTo
        this.items = items
        this.row = this.items.size()
        this.column = this.row
        double[][] twoDimensionArray = new double[this.row][this.column]
        this.matrix = new Matrix(twoDimensionArray)
        for(int i = 0;i<this.row;i++){
            for(int j = 0; j<this.column;j++){
                if(i==j)
                    matrix.set(i,j,1)
            }
        }
    }

    PairwaiseMatrix(String compareTo, ArrayList<String> items,ArrayList<Double> values) {
        this.compareTo = compareTo
        this.items = items
        this.row = this.items.size()
        this.column = this.row
        double[][] twoDimensionArray = new double[this.row][this.column]
        this.matrix = new Matrix(twoDimensionArray)
        for(int i = 0;i<this.row;i++){
            for(int j = 0; j<this.column;j++){
                matrix.set(i,j,values.get(i*this.row+j))
            }
        }
    }

    String getCompareTo() {
        return compareTo
    }

    void setCompareTo(String compareTo) {
        this.compareTo = compareTo
    }

    ArrayList<String> getItems() {
        return items
    }

    void setItems(ArrayList<String> items) {
        this.items = items
    }

    int getRow() {
        return row
    }

    void setRow(int row) {
        this.row = row
    }

    int getColumn() {
        return column
    }

    void setColumn(int column) {
        this.column = column
    }

    Matrix getMatrix() {
        return matrix
    }

    void setMatrix(Matrix matrix) {
        this.matrix = matrix
    }
}