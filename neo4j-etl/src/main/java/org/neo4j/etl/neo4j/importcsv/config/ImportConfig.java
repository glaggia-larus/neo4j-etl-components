package org.neo4j.etl.neo4j.importcsv.config;

import java.nio.file.Path;
import java.util.Collection;

import org.neo4j.etl.neo4j.importcsv.config.formatting.Formatting;
import org.neo4j.etl.neo4j.importcsv.config.formatting.ImportToolOptions;
import org.neo4j.etl.neo4j.importcsv.fields.IdType;
import org.neo4j.etl.process.Commands;
import org.neo4j.etl.process.CommandsSupplier;
import org.neo4j.etl.util.OperatingSystem;
import org.neo4j.etl.util.Preconditions;

public class ImportConfig implements CommandsSupplier
{

    public static Builder.SetImportToolDirectory builder()
    {
        return new ImportConfigBuilder();
    }

    public static final String IMPORT_TOOL = OperatingSystem.isWindows() ? "neo4j-import.bat" : "neo4j-import";

    private final ImportToolOptions importToolOptions;

    private final Path importToolDirectory;
    private final Path destination;
    private final Formatting formatting;
    private final IdType idType;
    private final Collection<NodeConfig> nodes;
    private final Collection<RelationshipConfig> relationships;

    ImportConfig( ImportConfigBuilder builder )
    {
        this.importToolDirectory = Preconditions.requireNonNull( builder.importToolDirectory, "ImportToolDirectory" );
        this.destination = Preconditions.requireNonNull( builder.destination, "Destination" );
        this.formatting = Preconditions.requireNonNull( builder.formatting, "Formatting" );
        this.idType = Preconditions.requireNonNull( builder.idType, "IdType" );
        this.nodes = builder.nodes;
        this.relationships = builder.relationships;
        this.importToolOptions = builder.importToolOptions;
    }

    @Override
    public void addCommandsTo( Commands.Builder.SetCommands commands )
    {
        commands.addCommand( importToolDirectory.resolve( IMPORT_TOOL ).toString() );

        commands.addCommand( "--into" );
        commands.addCommand( destination.toAbsolutePath().toString() );

        for ( NodeConfig node : nodes )
        {
            node.addCommandsTo( commands );
        }

        for ( RelationshipConfig relationship : relationships )
        {
            relationship.addCommandsTo( commands );
        }

        commands.addCommand( "--delimiter" );
        commands.addCommand( formatting.delimiter().description() );

        commands.addCommand( "--array-delimiter" );
        commands.addCommand( formatting.arrayDelimiter().description() );

        commands.addCommand( "--quote" );
        commands.addCommand( formatting.quote().argValue() );

        commands.addCommand( "--id-type" );
        commands.addCommand( idType.name().toUpperCase() );

        importToolOptions.addOptionsAsCommands( commands );
    }

    public interface Builder
    {
        interface SetImportToolDirectory
        {
            SetImportToolOptions importToolDirectory( Path directory );
        }

        interface SetImportToolOptions
        {
            SetDestination importToolOptions( ImportToolOptions importToolOptions );
        }

        interface SetDestination
        {
            SetFormatting destination( Path directory );
        }

        interface SetFormatting
        {
            SetIdType formatting( Formatting formatting );
        }

        interface SetIdType
        {
            Builder idType( IdType idType );
        }

        Builder addNodeConfig( NodeConfig nodeConfig );

        Builder addRelationshipConfig( RelationshipConfig relationshipConfig );

        ImportConfig build();
    }
}
