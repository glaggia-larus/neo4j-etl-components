package org.neo4j.integration.sql.exportcsv.mysql.schema;

import java.util.Collection;
import java.util.Iterator;

import org.junit.Test;

import org.neo4j.integration.io.AwaitHandle;
import org.neo4j.integration.sql.DatabaseClient;
import org.neo4j.integration.sql.QueryResults;
import org.neo4j.integration.sql.StubQueryResults;
import org.neo4j.integration.sql.exportcsv.mysql.MySqlDataType;
import org.neo4j.integration.sql.metadata.Column;
import org.neo4j.integration.sql.metadata.ColumnType;
import org.neo4j.integration.sql.metadata.Join;
import org.neo4j.integration.sql.metadata.TableName;
import org.neo4j.integration.sql.metadata.TableNamePair;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class JoinMetadataProducerTest
{
    @Test
    public void shouldReturnJoinMetadata() throws Exception
    {
        // given
        QueryResults results = StubQueryResults.builder()
                .columns( "TABLE_SCHEMA",
                        "TABLE_NAME",
                        "PRIMARY_KEY",
                        "FOREIGN_KEY",
                        "REFERENCED_TABLE_SCHEMA",
                        "REFERENCED_TABLE_NAME" )
                .addRow( "test", "Person", "id", "addressId", "test", "Address" )
                .addRow( "test", "Address", "id", "ownerId", "test", "Person" )
                .build();

        DatabaseClient databaseClient = mock( DatabaseClient.class );
        when( databaseClient.executeQuery( any( String.class ) ) ).thenReturn( AwaitHandle.forReturnValue( results ) );

        JoinMetadataProducer getJoinMetadata = new JoinMetadataProducer( databaseClient );

        // when
        Collection<Join> joins = getJoinMetadata
                .createMetadataFor( new TableNamePair(
                        new TableName( "test.Person" ),
                        new TableName( "test.Address" ) ) );

        // then
        Iterator<Join> iterator = joins.iterator();

        Join join1 = iterator.next();

        assertEquals( Column.builder()
                .table( new TableName( "test.Person" ) )
                .name( "test.Person.id" )
                .alias( "id" )
                .columnType( ColumnType.PrimaryKey )
                .dataType( MySqlDataType.TEXT )
                .build(), join1.primaryKey() );

        assertEquals( Column.builder()
                .table( new TableName( "test.Person" ) )
                .name( "test.Person.addressId" )
                .alias( "addressId" )
                .columnType( ColumnType.ForeignKey )
                .dataType( MySqlDataType.TEXT )

                .build(), join1.foreignKey() );

        assertEquals( new TableName( "test.Address" ), join1.childTable() );

        Join join2 = iterator.next();

        assertEquals( Column.builder()
                .table( new TableName( "test.Address" ) )
                .name( "test.Address.id" )
                .alias( "id" )
                .columnType( ColumnType.PrimaryKey )
                .dataType( MySqlDataType.TEXT )
                .build(), join2.primaryKey() );

        assertEquals( Column.builder()
                .table( new TableName( "test.Address" ) )
                .name( "test.Address.ownerId" )
                .alias( "ownerId" )
                .columnType( ColumnType.ForeignKey )
                .dataType( MySqlDataType.TEXT )
                .build(), join2.foreignKey() );

        assertEquals( new TableName( "test.Person" ), join2.childTable() );

        assertFalse( iterator.hasNext() );
    }
}
