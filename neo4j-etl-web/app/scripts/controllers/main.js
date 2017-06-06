'use strict';

/**
 * @ngdoc function
 * @name neo4jEtlWebApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the neo4jEtlWebApp
 */
angular.module('neo4jEtlWebApp')
    .controller('MainCtrl', ['_', function (_) {
        this.awesomeThings = [
      'HTML5 Boilerplate',
      'AngularJS',
      'Karma'
    ];

        this.mapping = [{
            'name': 'public.a',
            'graph-object-type': 'Node',
            //'sql': 'SELECT \'public\'.\'a\'.\'A_ID\' AS \'A_ID\', \'public\'.\'a\'.\'A_ID\' AS \'A_ID\', \'public\'.\'a\'.\'A_NAME\' AS \'A_NAME\', 'A' AS \'_NODE_LABEL_\' FROM \'public\'.\'a\'',
            'mappings': [{
                'column': {
                    'type': 'CompositeColumn',
                    'table': 'public.a',
                    'role': 'PrimaryKey',
                    'columns': [{
                        'type': 'SimpleColumn',
                        'role': 'Data',
                        'table': 'public.a',
                        'name': 'A_ID',
                        'alias': 'A_ID',
                        'sql-data-type': 'INT2',
                        'column-value-selection-strategy': 'SelectColumnValue'
      }]
                },
                'field': {
                    'type': 'Id',
                    'name': '',
                    'id-space': 'public.a'
                }
  }, {
                'column': {
                    'type': 'SimpleColumn',
                    'role': 'Data',
                    'table': 'public.a',
                    'name': 'A_ID',
                    'alias': 'A_ID',
                    'sql-data-type': 'INT2',
                    'column-value-selection-strategy': 'SelectColumnValue'
                },
                'field': {
                    'type': 'Data',
                    'name': 'A_ID',
                    'neo4j-data-type': 'Int',
                    'is-array': false
                }
  }, {
                'column': {
                    'type': 'SimpleColumn',
                    'role': 'Data',
                    'table': 'public.a',
                    'name': 'A_NAME',
                    'alias': 'A_NAME',
                    'sql-data-type': 'VARCHAR',
                    'column-value-selection-strategy': 'SelectColumnValue'
                },
                'field': {
                    'type': 'Data',
                    'name': 'A_NAME',
                    'neo4j-data-type': 'String',
                    'is-array': false
                }
  }, {
                'column': {
                    'type': 'SimpleColumn',
                    'role': 'Literal',
                    'table': 'public.a',
                    'name': '\'A\'',
                    'alias': '_NODE_LABEL_',
                    'sql-data-type': 'TEXT',
                    'column-value-selection-strategy': 'SelectColumnValue'
                },
                'field': {
                    'type': 'Label'
                }
  }]
}, {
            'name': 'public.b',
            'graph-object-type': 'Node',
            //'sql': 'SELECT \'public\'.\'b\'.\'B_ID\' AS \'B_ID\', \'public\'.\'b\'.\'B_ID\' AS \'B_ID\', \'public\'.\'b\'.\'B_NAME\' AS \'B_NAME\', 'B' AS \'_NODE_LABEL_\' FROM \'public\'.\'b\'',
            'mappings': [{
                'column': {
                    'type': 'CompositeColumn',
                    'table': 'public.b',
                    'role': 'PrimaryKey',
                    'columns': [{
                        'type': 'SimpleColumn',
                        'role': 'Data',
                        'table': 'public.b',
                        'name': 'B_ID',
                        'alias': 'B_ID',
                        'sql-data-type': 'INT2',
                        'column-value-selection-strategy': 'SelectColumnValue'
      }]
                },
                'field': {
                    'type': 'Id',
                    'name': '',
                    'id-space': 'public.b'
                }
  }, {
                'column': {
                    'type': 'SimpleColumn',
                    'role': 'Data',
                    'table': 'public.b',
                    'name': 'B_ID',
                    'alias': 'B_ID',
                    'sql-data-type': 'INT2',
                    'column-value-selection-strategy': 'SelectColumnValue'
                },
                'field': {
                    'type': 'Data',
                    'name': 'B_ID',
                    'neo4j-data-type': 'Int',
                    'is-array': false
                }
  }, {
                'column': {
                    'type': 'SimpleColumn',
                    'role': 'Data',
                    'table': 'public.b',
                    'name': 'B_NAME',
                    'alias': 'B_NAME',
                    'sql-data-type': 'VARCHAR',
                    'column-value-selection-strategy': 'SelectColumnValue'
                },
                'field': {
                    'type': 'Data',
                    'name': 'B_NAME',
                    'neo4j-data-type': 'String',
                    'is-array': false
                }
  }, {
                'column': {
                    'type': 'SimpleColumn',
                    'role': 'Literal',
                    'table': 'public.b',
                    'name': '\'B\'',
                    'alias': '_NODE_LABEL_',
                    'sql-data-type': 'TEXT',
                    'column-value-selection-strategy': 'SelectColumnValue'
                },
                'field': {
                    'type': 'Label'
                }
  }]
}, {
            'name': 'public.c',
            'graph-object-type': 'Node',
            //'sql': 'SELECT \'public\'.\'c\'.\'C_ID\' AS \'C_ID\', \'public\'.\'c\'.\'C_ID\' AS \'C_ID\', \'public\'.\'c\'.\'C_NAME\' AS \'C_NAME\', 'C' AS \'_NODE_LABEL_\' FROM \'public\'.\'c\'',
            'mappings': [{
                'column': {
                    'type': 'CompositeColumn',
                    'table': 'public.c',
                    'role': 'PrimaryKey',
                    'columns': [{
                        'type': 'SimpleColumn',
                        'role': 'Data',
                        'table': 'public.c',
                        'name': 'C_ID',
                        'alias': 'C_ID',
                        'sql-data-type': 'INT2',
                        'column-value-selection-strategy': 'SelectColumnValue'
      }]
                },
                'field': {
                    'type': 'Id',
                    'name': '',
                    'id-space': 'public.c'
                }
  }, {
                'column': {
                    'type': 'SimpleColumn',
                    'role': 'Data',
                    'table': 'public.c',
                    'name': 'C_ID',
                    'alias': 'C_ID',
                    'sql-data-type': 'INT2',
                    'column-value-selection-strategy': 'SelectColumnValue'
                },
                'field': {
                    'type': 'Data',
                    'name': 'C_ID',
                    'neo4j-data-type': 'Int',
                    'is-array': false
                }
  }, {
                'column': {
                    'type': 'SimpleColumn',
                    'role': 'Data',
                    'table': 'public.c',
                    'name': 'C_NAME',
                    'alias': 'C_NAME',
                    'sql-data-type': 'VARCHAR',
                    'column-value-selection-strategy': 'SelectColumnValue'
                },
                'field': {
                    'type': 'Data',
                    'name': 'C_NAME',
                    'neo4j-data-type': 'String',
                    'is-array': false
                }
  }, {
                'column': {
                    'type': 'SimpleColumn',
                    'role': 'Literal',
                    'table': 'public.c',
                    'name': '\'C\'',
                    'alias': '_NODE_LABEL_',
                    'sql-data-type': 'TEXT',
                    'column-value-selection-strategy': 'SelectColumnValue'
                },
                'field': {
                    'type': 'Label'
                }
  }]
}, {
            'name': 'public.c.C_ID_public.c.C_ID_B',
            'graph-object-type': 'Relationship',
            //'sql': 'SELECT \'public\'.\'c\'.\'C_ID\' AS \'C_ID\', \'public\'.\'c\'.\'C_ID_B\' AS \'C_ID_B\', 'B' AS \'_RELATIONSHIP_TYPE_\' FROM \'public\'.\'c\'',
            'mappings': [{
                'column': {
                    'type': 'CompositeColumn',
                    'table': 'public.c',
                    'role': 'PrimaryKey',
                    'columns': [{
                        'type': 'SimpleColumn',
                        'role': 'Data',
                        'table': 'public.c',
                        'name': 'C_ID',
                        'alias': 'C_ID',
                        'sql-data-type': 'INT2',
                        'column-value-selection-strategy': 'SelectColumnValue'
      }]
                },
                'field': {
                    'type': 'StartId',
                    'id-space': 'public.c'
                }
  }, {
                'column': {
                    'type': 'CompositeColumn',
                    'table': 'public.c',
                    'role': 'ForeignKey',
                    'columns': [{
                        'type': 'SimpleColumn',
                        'role': 'Data',
                        'table': 'public.c',
                        'name': 'C_ID_B',
                        'alias': 'C_ID_B',
                        'sql-data-type': 'INT2',
                        'column-value-selection-strategy': 'SelectColumnValue'
      }]
                },
                'field': {
                    'type': 'EndId',
                    'id-space': 'public.b'
                }
  }, {
                'column': {
                    'type': 'SimpleColumn',
                    'role': 'Literal',
                    'table': 'public.c',
                    'name': '\'B\'',
                    'alias': '_RELATIONSHIP_TYPE_',
                    'sql-data-type': 'TEXT',
                    'column-value-selection-strategy': 'SelectColumnValue'
                },
                'field': {
                    'type': 'RelationshipType'
                }
  }]
}, {
            'name': 'public.b.B_ID_public.b.B_ID_A',
            'graph-object-type': 'Relationship',
            //'sql': 'SELECT \'public\'.\'b\'.\'B_ID\' AS \'B_ID\', \'public\'.\'b\'.\'B_ID_A\' AS \'B_ID_A\', 'A' AS \'_RELATIONSHIP_TYPE_\' FROM \'public\'.\'b\'',
            'mappings': [{
                'column': {
                    'type': 'CompositeColumn',
                    'table': 'public.b',
                    'role': 'PrimaryKey',
                    'columns': [{
                        'type': 'SimpleColumn',
                        'role': 'Data',
                        'table': 'public.b',
                        'name': 'B_ID',
                        'alias': 'B_ID',
                        'sql-data-type': 'INT2',
                        'column-value-selection-strategy': 'SelectColumnValue'
      }]
                },
                'field': {
                    'type': 'StartId',
                    'id-space': 'public.b'
                }
  }, {
                'column': {
                    'type': 'CompositeColumn',
                    'table': 'public.b',
                    'role': 'ForeignKey',
                    'columns': [{
                        'type': 'SimpleColumn',
                        'role': 'Data',
                        'table': 'public.b',
                        'name': 'B_ID_A',
                        'alias': 'B_ID_A',
                        'sql-data-type': 'INT2',
                        'column-value-selection-strategy': 'SelectColumnValue'
      }]
                },
                'field': {
                    'type': 'EndId',
                    'id-space': 'public.a'
                }
  }, {
                'column': {
                    'type': 'SimpleColumn',
                    'role': 'Literal',
                    'table': 'public.b',
                    'name': '\'A\'',
                    'alias': '_RELATIONSHIP_TYPE_',
                    'sql-data-type': 'TEXT',
                    'column-value-selection-strategy': 'SelectColumnValue'
                },
                'field': {
                    'type': 'RelationshipType'
                }
  }]
}];

        this.getLabel = function (el) {
            if (el['graph-object-type'] === 'Node') {
                var ell = _.filter(el.mappings, function (e) {
                    return e.field.type === 'Label';
                });
                return ell[0].column.name;

            } else {
                var x = _.filter(el.mappings, function (e) {
                    return e.field.type === 'RelationshipType';
                });
                return x[0].column.name;
            }
        };

        this.setLabel = function (el, lbl) {
            if (el['graph-object-type'] === 'Node') {
                var ell = _.filter(el.mappings, function (e) {
                    return e.field.type === 'Label';
                });
                ell[0].column.name = lbl;

            } else {
                var x = _.filter(el.mappings, function (e) {
                    return e.field.type === 'RelationshipType';
                });
                x[0].column.name = lbl;
            }
            el.editing = false;
        };

        this.getFrom = function (el) {
            var s = '(';
            var x = _.filter(el.mappings, function (e) {
                return e.field.type === 'StartId';
            });
            s = s + x[0].field['id-space'] + ' )-[ ';
            return s;
        };

        this.getTo = function (el) {
            var s = ' ]->( ';
            var x = _.filter(el.mappings, function (e) {
                return e.field.type === 'EndId';
            });
            s = s + x[0].field['id-space'] + ' )';
            return s;
        };

        this.save = function (e, l, $event) {
            $event.stopPropagation();
            this.setLabel(e, l);
        };

    }]);