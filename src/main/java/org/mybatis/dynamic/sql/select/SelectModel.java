/**
 *    Copyright 2016-2017 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.mybatis.dynamic.sql.select;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;
import org.mybatis.dynamic.sql.select.join.JoinModel;
import org.mybatis.dynamic.sql.where.WhereModel;

public class SelectModel {
    private boolean isDistinct;
    private List<SqlColumn<?>> columns = new ArrayList<>();
    private SqlTable table;
    private Optional<JoinModel> joinModel;
    private Optional<WhereModel> whereModel;
    private Optional<List<SqlColumn<?>>> orderByColumns;

    private SelectModel() {
        super();
    }
    
    public boolean isDistinct() {
        return isDistinct;
    }
    
    public Stream<SqlColumn<?>> columns() {
        return columns.stream();
    }
    
    public SqlTable table() {
        return table;
    }
    
    public Optional<WhereModel> whereModel() {
        return whereModel;
    }
    
    public Optional<JoinModel> joinModel() {
        return joinModel;
    }
    
    public Optional<Stream<SqlColumn<?>>> orderByColumns() {
        return orderByColumns.flatMap(cl -> Optional.of(cl.stream()));
    }
    
    public static class Builder {
        private boolean isDistinct;
        private List<SqlColumn<?>> columns;
        private SqlTable table;
        private WhereModel whereModel;
        private List<SqlColumn<?>> orderByColumns;
        private JoinModel joinModel;
        
        public Builder isDistinct(boolean isDistinct) {
            this.isDistinct = isDistinct;
            return this;
        }

        public Builder withColumns(List<SqlColumn<?>> columns) {
            this.columns = columns;
            return this;
        }

        public Builder withTable(SqlTable table) {
            this.table = table;
            return this;
        }

        public Builder withWhereModel(WhereModel whereModel) {
            this.whereModel = whereModel;
            return this;
        }

        public Builder withOrderByColumns(List<SqlColumn<?>> columns) {
            orderByColumns = columns;
            return this;
        }
        
        public Builder withJoinModel(JoinModel joinModel) {
            this.joinModel = joinModel;
            return this;
        }
        
        public SelectModel build() {
            SelectModel selectModel = new SelectModel();
            selectModel.columns = columns;
            selectModel.isDistinct = isDistinct;
            selectModel.orderByColumns = Optional.ofNullable(orderByColumns);
            selectModel.table = table;
            selectModel.whereModel = Optional.ofNullable(whereModel);
            selectModel.joinModel = Optional.ofNullable(joinModel);
            return selectModel;
        }
    }
}
