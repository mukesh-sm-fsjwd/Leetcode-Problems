class Solution {
    public String evaluate(String s, List<List<String>> knowledge) {
        StringBuilder result = new StringBuilder();
        Map<String,String> map = new HashMap<>();

        for(List<String> list : knowledge){
            map.put(list.get(0),list.get(1));
        }

        int i = 0;
    
        while (i < s.length()) {
            char current = s.charAt(i);
        
            if (current == '(') {
                StringBuilder key = new StringBuilder();
                i++; 

                while (i < s.length() && s.charAt(i) != ')') {
                    key.append(s.charAt(i));
                    i++;
                }
                result.append(map.getOrDefault(key.toString(), "?"));
                i++; 
            } 
            else {
                result.append(current);
                i++;
            }
        }
        return result.toString();
    }
}
