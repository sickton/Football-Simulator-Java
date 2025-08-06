package Utils;

public class VectorTwoDim {
    private double x;
    private double y;

    public VectorTwoDim(double x, double y)
    {
        this.x = x;
        this.y = y;
    }

    public double distanceTo(VectorTwoDim other) {
        double dx = other.getX() - this.x;
        double dy = other.getY() - this.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public VectorTwoDim subtract(VectorTwoDim other) {
        return new VectorTwoDim(this.x - other.x, this.y - other.y);
    }

    public VectorTwoDim normalize() {
        double mag = Math.sqrt(x * x + y * y);
        if (mag == 0) return new VectorTwoDim(0, 0);
        return new VectorTwoDim(x / mag, y / mag);
    }

    public VectorTwoDim scale(double factor) {
        return new VectorTwoDim(x * factor, y * factor);
    }

    public VectorTwoDim add(VectorTwoDim other) {
        return new VectorTwoDim(this.x + other.x, this.y + other.y);
    }
}
