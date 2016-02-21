package ch03_lambda_library;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.util.List;
import java.util.Objects;
import java.util.function.UnaryOperator;

public class Lecture06 {
    public static void main(String[] args) {
        LatentImage.from(null).transform(Color::brighter).transform(Color::grayscale)
            .toImage();
    }

    public static class LatentImage {
        private Image in;
        private List<UnaryOperator<Color>> pendingOperations;

        public static LatentImage from(Image image) {
            final LatentImage latentImage = new LatentImage();
            latentImage.in = image;
            return latentImage;
        }

        public LatentImage transform(UnaryOperator<Color> f) {
            Objects.requireNonNull(f);
            pendingOperations.add(f);
            return this;
        }

        public Image toImage() {
            // ...
            return null;
        }
    }
}
