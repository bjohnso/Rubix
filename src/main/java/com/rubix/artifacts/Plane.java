package com.rubix.artifacts;

public class Plane {

    private String alias;
    private String color;
    private String polar;
    private Node nodes[][];

    public Plane(String alias, String color, String polar){
        setAlias(alias);
        setColor(color);
        setPolar(polar);
        initNodes();
    }

    public void initNodes() {
        Node temp[][] = {
                {new Node("DL", this.getColor()), new Node("DM", this.getColor()), new Node("DR", this.getColor())},
                {new Node("ML", this.getColor()), new Node("MM", this.getColor()), new Node("MR", this.getColor())},
                {new Node("UL", this.getColor()), new Node("UM", this.getColor()), new Node("UR", this.getColor())}
        };
        nodes = temp;
    }

    public void permutate(Plane right, Plane up, Plane left, Plane down, int direction) {
        //Complete Initial Permutation
        if (direction > 0) {
            for (int a = 0; a < 2; a++) {
                Node temp = nodes[0][0];
                for (int i = 0; i < nodes.length; i++) {
                    for (int j = 0; j < nodes[i].length; j++) {
                        if (i == 0) {
                            if (j < 2)
                                nodes[i][j] = nodes[i][j + 1];
                            else
                                nodes[i][j] = nodes[i + 1][nodes[i + 1].length - 1];
                        } else if (i == 1) {
                            j = nodes[i].length - 1;
                            nodes[i][j] = nodes[i + 1][nodes[i + 1].length - 1];
                        } else if (i == 2) {
                            if (j < 2)
                                nodes[i][nodes[i].length - 1 - j] = nodes[i][nodes.length - 2 - j];
                            else {
                                nodes[i][nodes[i].length - 1 - j] = nodes[i - 1][nodes[i].length - 1 - j];
                                nodes[i - 1][nodes[i].length - 1 - j] = temp;
                                break ;
                            }
                        }
                    }
                }
            }

            //Complete implications on adjacent planes
            Node rightTemp[] = {
                    right.getNodes()[0][0],
                    right.getNodes()[1][0],
                    right.getNodes()[2][0]
            };
            Node leftTemp[] = {
                    left.getNodes()[2][2],
                    left.getNodes()[1][2],
                    left.getNodes()[0][2]
            };
            if (this.alias.equalsIgnoreCase("F")
                || this.alias.equalsIgnoreCase("U")
                || this.alias.equalsIgnoreCase("D")) {
                for (int i = 0; i < nodes.length; i++) {
                    right.getNodes()[i][0] = up.getNodes()[i][nodes[i].length - 1 - i];
                    left.getNodes()[nodes[i].length - 1 - i][nodes[i].length - 1] = down.getNodes()[nodes.length - 1][nodes[i].length - 1 - i];
                }

                for (int i = 0; i < nodes.length; i++) {
                    up.getNodes()[0][nodes.length - 1 - i] = leftTemp[i];
                    down.getNodes()[nodes.length - 1][i] = rightTemp[i];
                }
            }
            else if (this.alias.equalsIgnoreCase("R")){
                for (int i = 0; i < nodes.length; i++) {
                    right.getNodes()[i][0] = up.getNodes()[nodes.length - 1 - i][nodes[i].length - 1];
                    left.getNodes()[nodes[i].length - 1 - i][nodes[i].length - 1] = down.getNodes()[nodes[i].length - 1 - i][nodes[i].length - 1];
                }

                for (int i = 0; i < nodes.length; i++) {
                    up.getNodes()[nodes.length - 1 - i][nodes.length - 1] = leftTemp[i];
                    down.getNodes()[nodes.length - 1 - i][nodes.length - 1] = rightTemp[i];
                }
            }
            else if (this.alias.equalsIgnoreCase("L")){
                for (int i = 0; i < nodes.length; i++) {
                    right.getNodes()[i][0] = up.getNodes()[i][0];
                    left.getNodes()[i][nodes[i].length - 1] = down.getNodes()[nodes.length - 1 - i][0];
                }

                for (int i = 0; i < nodes.length; i++) {
                    up.getNodes()[i][0] = leftTemp[i];
                    down.getNodes()[i][0] = rightTemp[i];
                }
            }

        }
    }

    public void setPolar(String polar) {
        this.polar = polar;
    }

    public String getPolar() {
        return polar;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getAlias() {
        return alias;
    }

    public String getColor() {
        return color;
    }

    public Node[][] getNodes() {
        return nodes;
    }

    public void printPlane() {
        System.out.println(this.getAlias());
        for (int i = nodes.length - 1; i >= 0; i--){
            for (int j = 0; j < nodes[i].length; j++){
                System.out.print(nodes[i][j].getColor() + ", ");
            }
            System.out.print("\n");
        }
    }
}
