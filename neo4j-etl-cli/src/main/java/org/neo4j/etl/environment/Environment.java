package org.neo4j.etl.environment;

import java.nio.file.Path;

import org.neo4j.etl.neo4j.importcsv.config.formatting.ImportToolOptions;

public class Environment
{
    private final Path importToolDirectory;
    private final Path destinationDirectory;
    private final Path csvDirectory;
    private final ImportToolOptions importToolOptions;

    public Environment( Path importToolDirectory,
                        Path destinationDirectory,
                        Path csvDirectory,
                        ImportToolOptions importToolOptions )
    {
        this.importToolDirectory = importToolDirectory;
        this.destinationDirectory = destinationDirectory;
        this.csvDirectory = csvDirectory;
        this.importToolOptions = importToolOptions;
    }

    public Path importToolDirectory()
    {
        return importToolDirectory;
    }

    public Path destinationDirectory()
    {
        return destinationDirectory;
    }

    public Path csvDirectory()
    {
        return csvDirectory;
    }

    public ImportToolOptions importToolOptions()
    {
        return importToolOptions;
    }
}
