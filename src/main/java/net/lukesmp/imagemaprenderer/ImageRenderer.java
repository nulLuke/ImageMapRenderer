package net.lukesmp.imagemaprenderer;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.map.MapCanvas;
import org.bukkit.map.MapRenderer;
import org.bukkit.map.MapView;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class ImageRenderer extends MapRenderer {
    private BufferedImage image;

    public ImageRenderer() {}

    public ImageRenderer(String imagePath) {
        load(imagePath);
    }

    public boolean load(String imagePath) {
        if (isValid(imagePath)) {
            BufferedImage image = null;
            try {
                image = ImageIO.read(new URL(imagePath));
            } catch (IOException e) {
                return false;
            }
            this.image = image;
            return true;
        }
        return false;
    }

    public static boolean isValid(String url) {
        try {
            new URL(url).toURI();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void render(MapView mapView, MapCanvas mapCanvas, Player player) {
        if (image != null) {
            mapCanvas.drawImage(0, 0, image);
            mapView.setTrackingPosition(false);
        }
    }
}