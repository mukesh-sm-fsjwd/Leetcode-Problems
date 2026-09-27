class Solution {
    public String reverseParentheses(String s) {
        Stack<Character> stack = new Stack<>();

        for(int i = 0;i < s.length();i++){
            char c = s.charAt(i);
            if(c == ')'){
                StringBuilder sb = new StringBuilder();
                while(!stack.isEmpty() && stack.peek() != '('){
                    sb.append(stack.pop());
                }
                if(!stack.isEmpty()){
                    stack.pop();
                }
                for(int j = 0;j < sb.length();j++){
                    stack.push(sb.charAt(j));
                }
            }else{
                stack.push(c);
            }
        }
        StringBuilder result = new StringBuilder();
        while(!stack.isEmpty()){
            result.append(stack.pop());
        }

        return result.reverse().toString();
    }
}
