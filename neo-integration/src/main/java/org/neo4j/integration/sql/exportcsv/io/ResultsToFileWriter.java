package org.neo4j.integration.sql.exportcsv.io;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;

import org.apache.commons.lang3.StringUtils;

import org.neo4j.integration.neo4j.importcsv.config.Formatting;
import org.neo4j.integration.sql.QueryResults;
import org.neo4j.integration.sql.exportcsv.mapping.ColumnToCsvFieldMappings;
import org.neo4j.integration.sql.exportcsv.mapping.CsvResource;
import org.neo4j.integration.sql.metadata.Column;

public class ResultsToFileWriter
{
    private Formatting formatting;

    public ResultsToFileWriter( Formatting formatting )
    {
        this.formatting = formatting;
    }

    public void write( QueryResults results, Path file, CsvResource resource ) throws Exception
    {
        RowStrategy rowStrategy = RowStrategy.select( resource.graphObjectType() );

        ColumnToCsvFieldMappings mappings = resource.mappings();
        Column[] columns = mappings.columns().toArray( new Column[mappings.columns().size()] );

        int maxIndex = columns.length - 1;

        try ( BufferedWriter writer = Files.newBufferedWriter( file, Charset.forName( "UTF8" ) ) )
        {
            while ( results.next() )
            {
                if ( rowStrategy.test( results, mappings.columns() ) )
                {
                    for ( int i = 0; i < maxIndex; i++ )
                    {
                        writeFieldValueAndDelimiter( columns[i].selectFrom( results ), writer, columns[i].useQuotes() );
                    }

                    writeFieldValueAndNewLine( columns[maxIndex].selectFrom( results ),
                            writer, columns[maxIndex].useQuotes() );
                }
            }
        }
    }

    private void writeFieldValueAndDelimiter( String value,
                                              BufferedWriter writer,
                                              boolean useQuotes ) throws IOException
    {
        sanitiseAndWriteData( value, writer, useQuotes );
        writer.write( this.formatting.delimiter().value() );
    }

    private void writeFieldValueAndNewLine( String value,
                                            BufferedWriter writer,
                                            boolean useQuotes ) throws IOException
    {
        sanitiseAndWriteData( value, writer, useQuotes );
        writer.newLine();
    }

    private void sanitiseAndWriteData( String value,
                                       BufferedWriter writer,
                                       boolean useQuotes ) throws IOException
    {

        if ( StringUtils.isNotEmpty( value ) )
        {
            if ( useQuotes )
            {
                this.formatting.quote().writeEnquoted( value, writer );
            }
            else
            {
                writer.write( value );
            }
        }
    }
}
