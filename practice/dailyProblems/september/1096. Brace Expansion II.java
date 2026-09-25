class Solution {
    private TreeSet<String> resultSet = new TreeSet<>();

    public List<String> braceExpansionII(String expression) {
        dfs(expression);
        return new ArrayList<>(resultSet);
    }

    private void dfs(String exp) {
        int j = exp.indexOf('}');
        // Base case: no more braces, add the fully expanded string
        if (j == -1) {
            resultSet.add(exp);
            return;
        }
        
        // Find the matching opening brace '{' for the first '}'
        int i = exp.lastIndexOf('{', j);
        String prefix = exp.substring(0, i);
        String suffix = exp.substring(j + 1);
        
        // Split the comma-separated options inside the braces and recurse
        for (String option : exp.substring(i + 1, j).split(",")) {
            dfs(prefix + option + suffix);
        }
    }
}
