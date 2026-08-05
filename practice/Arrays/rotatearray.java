class Solution {
    public List<Integer> remainingMethods(int n, int k, int[][] invocations) {
        //1.Create a graph and reverse it.
        List<List<Integer>> graph = new ArrayList<>();

        for(int i = 0;i < n;i++){
            graph.add(new ArrayList<>());
        }

        //2.Fill the graph with invocations.
        for(int[] invocation : invocations){
            int ai = invocation[0];
            int bi = invocation[1];
            graph.get(ai).add(bi);
        }

        //3.Use DFS to find all the suspicious methods that are invoked directly or indirectly by k.
        boolean[] suspecious = new boolean[n];
        dfsMarkSuspecious(graph,k,suspecious);

        //5.Check if any external method invokes the suspecious method.
        //If any non suspecious method invokes the suspecious method then we cannot remove that suspecious method because it becames nonsuspecious now.
        for(int i = 0;i < n;i++){
            //check for external invocations from non suspecious methods.
            if(!suspecious[i]){
                for(int neighbor : graph.get(i)){
                    if(suspecious[neighbor]){
                        //If external method invokes suspecious method then removal is not possible
                        List<Integer> allMethods = new ArrayList<>();
                        for(int j = 0;j < n;j++){
                            allMethods.add(j);
                        }
                        return allMethods;
                    }
                }
            }
        }

        //6.Collecting and returning remaining methods that are non suspecious
        List<Integer> remaining = new ArrayList<>();
        for(int i = 0;i < n;i++){
            if(!suspecious[i]){
                remaining.add(i);
            }
        } 
        return remaining;
    }

    //4. DFS to mark all the methods true if it is suspecious.
    private void dfsMarkSuspecious(List<List<Integer>> graph, int node, boolean[] suspecious){
        suspecious[node] = true;
        for(int neighbor : graph.get(node)){
            if(!suspecious[neighbor]){
                dfsMarkSuspecious(graph,neighbor,suspecious);
            }
        }
    }
}
