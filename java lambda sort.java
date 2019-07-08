Collections.sort(cities[i].edges, new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    int s_o1 = cities[o1].edges.size();
                    int s_o2 = cities[o2].edges.size();
                    if(s_o1 < s_o2) return 1;
                    else if(s_o1 == s_o2) return 0;
                    else return -1;
                }
            });
