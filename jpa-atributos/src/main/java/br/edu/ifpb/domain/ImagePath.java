package br.edu.ifpb.domain;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 02/08/2018, 09:43:50
 */
public class ImagePath {

    private Path path;

    public ImagePath(Path path) {
        this.path = path;
    }

    public ImagePath(String path) {
        this(Paths.get(path));
    }

    public byte[] toBytes() {
        try {
            return Files.readAllBytes(path);
        } catch (IOException ex) {
            return new byte[0];
        }
    }

}
