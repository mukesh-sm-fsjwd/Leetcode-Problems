class Solution {
    public boolean checkOverlap(int radius, int xCenter, int yCenter, int x1, int y1, int x2, int y2) {
        // Step 1: Find the X and Y coordinates of the closest point on the rectangle
        int closestX = Math.max(x1, Math.min(xCenter, x2));
        int closestY = Math.max(y1, Math.min(yCenter, y2));

        // Step 2: Calculate the distance components between the circle center and closest point
        int distanceX = xCenter - closestX;
        int distanceY = yCenter - closestY;

        // Step 3: Check if the squared distance is within the squared radius
        // We use squared values to avoid slow and imprecise Math.sqrt() operations
        int squaredDistance = (distanceX * distanceX) + (distanceY * distanceY);
        int squaredRadius = radius * radius;

        return squaredDistance <= squaredRadius;
    }
}
