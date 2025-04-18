public class ParticleAdapter extends Obstacle{
        private Particle particle;

        public ParticleAdapter(Particle particle) {
            super(particle.getX(), particle.getY(), particle.getRadius());
            this.particle = particle;
        }

        @Override
        public double getX() {
            return particle.getX();
        }

        @Override
        public double getY() {
            return particle.getY();
        }

        @Override
        public double getRadius() {
            return particle.getRadius();
        }

        @Override
        public void setRadius(double r) {
            particle.setRadius(r);
        }

        @Override
        public void setPositionX(double px) {
            particle.setPositionX(px);
        }

        @Override
        public void setPositionY(double py) {
            particle.setPositionY(py);
        }

        @Override
        public void setPosition(double px, double py) {
            particle.setPosition(px, py);
        }

        @Override
        public boolean checkCollision(Particle p) {
            double distance = Vector2DMath.magnitude(p.getX() - this.getX(), p.getY() - this.getY());
            return distance <= this.getRadius() + p.getRadius();
        }

        @Override
        public double[] collide(Particle p) {
            double[] normal = Vector2DMath.normal(p.getX() - this.getX(), p.getY() - this.getY());
            double overlap = this.getRadius() + p.getRadius() - Vector2DMath.magnitude(p.getX() - this.getX(), p.getY() - this.getY());
            p.setPositionX(p.getX() + normal[0] * overlap);
            p.setPositionY(p.getY() + normal[1] * overlap);
            return normal;
        }
    }
