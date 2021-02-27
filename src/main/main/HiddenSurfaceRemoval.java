package main;

import java.util.ArrayList;
import java.util.List;

public class HiddenSurfaceRemoval {


    public HiddenSurfaceRemoval() {

    }

    static class Instance {
        int xl;
        int xr;
        int depth;
        String color;

        public Instance(int xl, int xr, int depth, String color) {
            this.xl = xl;
            this.xr = xr;
            this.depth = depth;
            this.color = color;
        }

    }


    public List<Instance> removal(List<Instance> list, int lo, int hi) {
        if (lo < hi) {
            int pi = (lo + hi) / 2;
            List<Instance> scene1 = removal(list, lo, pi);
            List<Instance> scene2 = removal(list, pi + 1, hi);
            return merge(scene1, scene2);
        } else {
            List<Instance> temp = new ArrayList<>();
            temp.add(list.get(lo));
            return temp;
        }


    }

    public List<Instance> merge(List<Instance> scene1, List<Instance> scene2) {
        int i = 0;
        int j = 0;
        int xmin = -10;
        List<Instance> output = new ArrayList<>();


        while (i < scene1.size() && j < scene2.size()) {

            // case1
            if (xmin < scene1.get(i).xl && xmin < scene2.get(j).xl) {
                if (scene1.get(i).xl <= scene2.get(j).xl) {
                    xmin = scene1.get(i).xl;
                } else {
                    xmin = scene2.get(j).xl;
                }
                // case2a
            } else if (xmin >= scene1.get(i).xl && xmin < scene2.get(j).xl) {
                if (scene1.get(i).xr <= scene2.get(j).xl) {
                    addInstance(new Instance(xmin, scene1.get(i).xr, scene1.get(i).depth, scene1.get(i).color), output);
                    xmin = scene1.get(i).xr;
                    i++;
                } else {
                    addInstance(new Instance(xmin, scene2.get(j).xl, scene1.get(i).depth, scene1.get(i).color), output);
                    xmin = scene2.get(j).xl;
                }
                // case2b
            } else if (xmin >= scene2.get(j).xl && xmin < scene1.get(i).xl) {
                if (scene2.get(j).xr < scene1.get(i).xl) {
                    addInstance(new Instance(xmin, scene2.get(j).xr, scene2.get(j).depth, scene2.get(j).color), output);
                    xmin = scene2.get(j).xr;
                    j++;
                } else {
                    addInstance(new Instance(xmin, scene1.get(i).xl, scene2.get(j).depth, scene2.get(j).color), output);
                    xmin = scene1.get(i).xl;
                }
            } else {
                // case3a
                if (scene1.get(i).xr <= scene2.get(j).xr) {
                    if (scene1.get(i).depth < scene2.get(j).depth) {
                        addInstance(new Instance(xmin, scene1.get(i).xr, scene1.get(i).depth, scene1.get(i).color), output);
                    } else {
                        addInstance(new Instance(xmin, scene1.get(i).xr, scene2.get(j).depth, scene2.get(j).color), output);
                    }
                    xmin = scene1.get(i).xr;
                    i++;
                    // case3b
                } else {
                    if (scene1.get(i).depth <= scene2.get(j).depth) {
                        addInstance(new Instance(xmin, scene2.get(j).xr, scene1.get(i).depth, scene1.get(i).color), output);
                    } else {
                        addInstance(new Instance(xmin, scene2.get(j).xr, scene2.get(j).depth, scene2.get(j).color), output);
                    }
                    xmin = scene2.get(j).xr;
                    j++;

                }
            }
        }

        while (i < scene1.size()) {
            if (xmin < scene1.get(i).xl) {
                xmin = scene1.get(i).xl;
                addInstance(new Instance(xmin, scene1.get(i).xr, scene1.get(i).depth, scene1.get(i).color), output);
                i++;
            } else {
                addInstance(new Instance(xmin, scene1.get(i).xr, scene1.get(i).depth, scene1.get(i).color), output);
                xmin = scene1.get(i).xr;
                i++;
            }

        }

        while (j < scene2.size()) {
            if (xmin < scene2.get(j).xl) {
                xmin = scene2.get(j).xl;
                addInstance(new Instance(xmin, scene2.get(j).xr, scene2.get(j).depth, scene2.get(j).color), output);
                j++;
            } else {
                addInstance(new Instance(xmin, scene2.get(j).xr, scene2.get(j).depth, scene2.get(j).color), output);
                xmin = scene2.get(j).xr;
                j++;
            }

        }

        return output;
    }


    public void addInstance (Instance ins, List<Instance> list) {
        if (list.size() >= 1) {
            if (ins.color.equals(list.get(list.size() - 1).color) && ins.xl == list.get(list.size() - 1).xr && ins.depth == list.get(list.size() - 1).depth) {
                list.get(list.size() - 1).xr = ins.xr;
            } else {
                list.add(ins);
            }
        } else  {
            list.add(ins);
        }
    }

    public void printInstance(Instance ins) {
        System.out.println(ins.xl + "," + ins.xr + "," + ins.depth + "," + ins.color);
    }

}


class runHSR {
    public static void main(String[] args) {
        HiddenSurfaceRemoval hsr = new HiddenSurfaceRemoval();
        HiddenSurfaceRemoval.Instance ins1 = new HiddenSurfaceRemoval.Instance(2, 5, 1, "red");
        HiddenSurfaceRemoval.Instance ins2 = new HiddenSurfaceRemoval.Instance(9, 10, 6, "red");
        HiddenSurfaceRemoval.Instance ins3 = new HiddenSurfaceRemoval.Instance(0, 4, 3, "blue");
        HiddenSurfaceRemoval.Instance ins4 = new HiddenSurfaceRemoval.Instance(1, 8, 5, "green");
        HiddenSurfaceRemoval.Instance ins5 = new HiddenSurfaceRemoval.Instance(0, 1, 0, "green");

        List<HiddenSurfaceRemoval.Instance> list = new ArrayList<>();
        list.add(ins1);
        list.add(ins2);
        list.add(ins3);
        list.add(ins4);
        list.add(ins5);

        List<HiddenSurfaceRemoval.Instance> output = hsr.removal(list, 0, list.size() - 1);
        for (int i = 0; i < output.size(); i++) {
            hsr.printInstance(output.get(i));
        }

    }
}
