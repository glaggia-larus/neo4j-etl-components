package org.neo4j.integration.sql.metadata;

import java.util.Collections;
import java.util.Optional;

import com.fasterxml.jackson.databind.JsonNode;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import org.neo4j.integration.sql.RowAccessor;
import org.neo4j.integration.sql.exportcsv.mapping.ColumnToCsvFieldMappings;

import static java.util.Arrays.asList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TableInfoTest
{
    @Test
    public void collectionWithNoKeysDoesNotRepresentJoinTable()
    {
        // given
        TableInfo tableInfo =
                new TableInfo( Optional.empty(), Collections.emptyList(), Collections.emptyList() );

        // then
        assertFalse( tableInfo.representsJoinTable() );
    }

    @Test
    public void collectionWithPrimaryKeyAndNoForeignKeysDoesNotRepresentJoinTable()
    {
        // given
        TableInfo tableInfo = new TableInfo(
                Optional.of( new StubColumn( "javabase.Author.id" ) ),
                Collections.emptyList(),
                Collections.emptyList() );

        // then
        assertFalse( tableInfo.representsJoinTable() );
    }

    @Test
    public void collectionWithTwoForeignKeysAndNoPrimaryKeyRepresentsJoinTable()
    {
        // given
        TableInfo tableInfo = new TableInfo(
                Optional.empty(),
                asList( new JoinKey( new StubColumn( "javabase.Author_Publisher.author_id" ), null ),
                        new JoinKey( new StubColumn( "javabase.Author_Publisher.publisher_id" ), null ) ),
                Collections.emptyList() );

        // then
        assertTrue( tableInfo.representsJoinTable() );
    }

    @Test
    public void collectionWithOneForeignKeyAndNoPrimaryKeyDoesNotRepresentJoinTable()
    {
        // given
        TableInfo tableInfo = new TableInfo(
                Optional.empty(),
                Collections.singletonList(
                        new JoinKey( new StubColumn( "javabase.Author_Publisher.author_id" ), null ) ),
                Collections.emptyList() );

        // then
        assertFalse( tableInfo.representsJoinTable() );
    }

    @Test
    public void collectionWithThreeForeignKeysAndNoPrimaryKeyDoesNotRepresentJoinTable()
    {
        // given
        TableInfo tableInfo = new TableInfo(
                Optional.empty(),
                asList( new JoinKey( new StubColumn( "javabase.Author_Publisher.author_id" ), null ),
                        new JoinKey( new StubColumn( "javabase.Author_Publisher.publisher_id" ), null ),
                        new JoinKey( new StubColumn( "javabase.Author_Publisher.book_id" ), null ) ),
                Collections.emptyList() );

        // then
        assertFalse( tableInfo.representsJoinTable() );
    }

    @Test
    public void collectionWithCompositePrimaryKeyThatComprisesBothForeignKeysRepresentsJoinTable()
    {
        // given
        TableInfo tableInfo = new TableInfo(
                Optional.of( new StubColumn(
                        join( "javabase.Author_Publisher.author_id", "javabase.Author_Publisher.publisher_id" ) ) ),
                asList( new JoinKey( new StubColumn( "javabase.Author_Publisher.author_id" ), null ),
                        new JoinKey( new StubColumn( "javabase.Author_Publisher.publisher_id" ), null ) ),
                Collections.emptyList() );

        // then
        assertTrue( tableInfo.representsJoinTable() );
    }

    @Test
    public void collectionWithCompositePrimaryKeyThatComprisesSubsetOfBothForeignKeysRepresentsJoinTable()
    {
        // given
        TableInfo tableInfo = new TableInfo(
                Optional.of( new StubColumn( "javabase.Author_Publisher.author_id" ) ),
                asList( new JoinKey( new StubColumn( "javabase.Author_Publisher.author_id" ), null ),
                        new JoinKey( new StubColumn( "javabase.Author_Publisher.publisher_id" ), null ) ),
                Collections.emptyList() );

        // then
        assertTrue( tableInfo.representsJoinTable() );
    }

    @Test
    public void collectionWithCompositePrimaryKeyThatIntersectsBothForeignKeysDoesNotRepresentJoinTable()
    {
        // given
        TableInfo tableInfo = new TableInfo(
                Optional.of( new StubColumn(
                        join( "javabase.Author_Publisher.author_id", "javabase.Author_Publisher.sequence" ) ) ),
                asList( new JoinKey( new StubColumn( "javabase.Author_Publisher.author_id" ), null ),
                        new JoinKey( new StubColumn( "javabase.Author_Publisher.publisher_id" ), null ) ),
                Collections.emptyList() );

        // then
        assertFalse( tableInfo.representsJoinTable() );
    }

    @Test
    public void collectionWithCompositePrimaryKeyThatComprisesBothCompositeForeignKeysRepresentsJoinTable()
    {
        // given
        TableInfo tableInfo = new TableInfo(
                Optional.of( new StubColumn(
                        join( "javabase.Example.column_1",
                                "javabase.Example.column_2",
                                "javabase.Example.column_3" ) ) ),
                asList( new JoinKey( new StubColumn(
                                join( "javabase.Example.column_1", "javabase.Example.column_2" ) ), null ),
                        new JoinKey( new StubColumn( "javabase.Example.column_3" ), null ) ),
                Collections.emptyList() );

        // then
        assertTrue( tableInfo.representsJoinTable() );
    }

    @Test
    public void collectionWithCompositePrimaryKeyThatComprisesSubsetOfBothCompositeForeignKeysRepresentsJoinTable()
    {
        // given
        TableInfo tableInfo = new TableInfo(
                Optional.of( new StubColumn(
                        join( "javabase.Example.column_1",
                                "javabase.Example.column_2",
                                "javabase.Example.column_3" ) ) ),
                asList( new JoinKey( new StubColumn(
                                join( "javabase.Example.column_1", "javabase.Example.column_2" ) ), null ),
                        new JoinKey( new StubColumn(
                                join( "javabase.Example.column_3", "javabase.Example.column_4" ) ), null ) ),
                Collections.emptyList() );

        // then
        assertTrue( tableInfo.representsJoinTable() );
    }

    @Test
    public void collectionWithCompositePrimaryKeyThatIntersectsBothCompositeForeignKeysDoesNotRepresentJoinTable()
    {
        // given
        TableInfo tableInfo = new TableInfo(
                Optional.of( new StubColumn(
                        join( "javabase.Example.column_1",
                                "javabase.Example.column_2",
                                "javabase.Example.column_5" ) ) ),
                asList( new JoinKey( new StubColumn(
                                join( "javabase.Example.column_1", "javabase.Example.column_2" ) ), null ),
                        new JoinKey( new StubColumn(
                                join( "javabase.Example.column_3", "javabase.Example.column_4" ) ), null ) ),
                Collections.emptyList() );

        // then
        assertFalse( tableInfo.representsJoinTable() );
    }

    @Test
    public void shouldReturnCollectionOfColumns()
    {
        // given
        Column column1 = new StubColumn( "column-1" );
        Column column2 = new StubColumn( "column-2" );

        TableInfo tableInfo = new TableInfo(
                Optional.empty(),
                Collections.emptyList(),
                asList( column1, column2 ) );

        // then
        assertEquals( tableInfo.columns(), asList( column1, column2 ) );
    }
    @Test
    public void shouldReturnCollectionOfColumnsLessPrimaryKeyColumn()
    {
        // given
        Column pk = new StubColumn( "pk" );
        Column column1 = new StubColumn( "column-1" );
        Column column2 = new StubColumn( "column-2" );

        TableInfo tableInfo = new TableInfo(
                Optional.of( pk ),
                Collections.emptyList(),
                asList( pk, column1, column2 ) );

        // then
        assertEquals( tableInfo.columnsLessKeys(), asList( column1, column2 ) );
    }

    @Test
    public void shouldReturnCollectionOfColumnsLessCompositePrimaryKeyColumn()
    {
        // given
        Column pk1 = new StubColumn( "pk-1" );
        Column pk2 = new StubColumn( "pk-2" );
        Column column1 = new StubColumn( "column-1" );
        Column column2 = new StubColumn( "column-2" );

        TableInfo tableInfo = new TableInfo(
                Optional.of( new StubColumn( join( "pk-1", "pk-2" ) ) ),
                Collections.emptyList(),
                asList( pk1, pk2, column1, column2) );

        // then
        assertEquals( tableInfo.columnsLessKeys(), asList( column1, column2 ) );
    }

    @Test
    public void shouldReturnCollectionOfColumnsLessForeignKeyColumns()
    {
        // given
        Column column1 = new StubColumn( "column-1" );
        Column column2 = new StubColumn( "column-2" );
        Column fk1 = new StubColumn( "fk-1" );
        Column fk2 = new StubColumn( "fk-2" );

        TableInfo tableInfo = new TableInfo(
                Optional.empty(),
                asList( new JoinKey( new StubColumn( "fk-1" ), null ), new JoinKey( new StubColumn( "fk-2" ), null ) ),
                asList( fk1, column1, column2, fk2 ) );

        // then
        assertEquals( tableInfo.columnsLessKeys(), asList( column1, column2 ) );
    }

    @Test
    public void shouldReturnCollectionOfColumnsLessCompositeForeignKeyColumns()
    {
        // given
        Column column1 = new StubColumn( "column-1" );
        Column column2 = new StubColumn( "column-2" );
        Column fk1 = new StubColumn( "fk-1" );
        Column fk2 = new StubColumn( "fk-2" );

        TableInfo tableInfo = new TableInfo(
                Optional.empty(),
                Collections.singletonList( new JoinKey( new StubColumn( join( "fk-1", "fk-2" ) ), null ) ),
                asList( fk1, column1, column2, fk2 ) );

        // then
        assertEquals( tableInfo.columnsLessKeys(), asList( column1, column2 ) );
    }

    private String join( String... columns )
    {
        return StringUtils.join( columns, CompositeColumn.SEPARATOR );
    }

    private static class StubColumn implements Column
    {
        private final String name;

        private StubColumn( String name )
        {
            this.name = name;
        }

        @Override
        public TableName table()
        {
            return null;
        }

        @Override
        public String name()
        {
            return name;
        }

        @Override
        public String alias()
        {
            return null;
        }

        @Override
        public ColumnRole role()
        {
            return null;
        }

        @Override
        public SqlDataType sqlDataType()
        {
            return null;
        }

        @Override
        public String selectFrom( RowAccessor row )
        {
            return null;
        }

        @Override
        public String aliasedColumn()
        {
            return null;
        }

        @Override
        public void addData( ColumnToCsvFieldMappings.Builder builder )
        {

        }

        @Override
        public JsonNode toJson()
        {
            return null;
        }

        @Override
        public boolean useQuotes()
        {
            return false;
        }
    }
}
