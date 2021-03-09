<template>
    <div>
        <!--委托列表-->
        <el-table
                :data="
                    tableData.slice(
                        (query.currentPage-1) * query.pageSize,
                        query.currentPage * query.pageSize
                    )
                "
                border
                :cell-style="cellStyle"
                @sort-change="changeTableSort"
                :default-sort="{prop:'time',order:'descending'}"
        >
            <!--委托时间 股票代码 名称 委托价格 委托数量 方向 状态-->
            <el-table-column prop="time" label="委托时间" align="center"
                             sortable :sort-orders="['ascending','descending']"
            />
            <el-table-column prop="code" label="股票代码" align="center"/>
            <el-table-column prop="name" label="名称" align="center"/>
            <el-table-column prop="price" label="委托价格" align="center"/>
            <el-table-column prop="occount" label="委托数量" align="center"/>
            <el-table-column prop="direction" label="方向" align="center"/>
            <el-table-column prop="status" label="状态" align="center"/>
            <!--柜台开发时：补充撤单按钮-->
        </el-table>

        <!--分页控件——刷新-->
        <div class="pagination">
            <el-button round
                       type="primary" size="mini"
                       style="margin-top: 2px;float:right;"
                       icon="el-icon-refresh"
                       @click="">
                刷新
            </el-button>
            <el-pagination
                    background
                    layout="total,prev,pager,next"
                    :current-page="query.currentPage"
                    :page-size="query.pageSize"
                    :total="dataTotalCount"
                    @current-change="handlePageChange"
            />
        </div>
    </div>
</template>

<script>
    export default {
        name: "OrderList",
        data() {
            return {
                tableData: [
                    {
                        time: '09:40:00',
                        code: '000001',
                        name: '平安银行',
                        price: '100',
                        occount: 15,
                        direction: '买入',
                        status: 1
                    },
                    {
                        time: '09:50:00',
                        code: '000001',
                        name: '平安银行',
                        price: '110',
                        occount: 31,
                        direction: '买入',
                        status: 3
                    },
                    {
                        time: '09:52:00',
                        code: '000001',
                        name: '平安银行',
                        price: '102',
                        occount: 4,
                        direction: '买入',
                        status: 1
                    },
                    {
                        time: '09:56:00',
                        code: '000001',
                        name: '平安银行',
                        price: '120',
                        occount: 12,
                        direction: '买入',
                        status: 2
                    },
                    {
                        time: '10:26:00',
                        code: '000001',
                        name: '平安银行',
                        price: '105',
                        occount: 13,
                        direction: '买入',
                        status: 1
                    },
                    {
                        time: '10:47:00',
                        code: '000001',
                        name: '平安银行',
                        price: '106',
                        occount: 10,
                        direction: '买入',
                        status: 3
                    },
                    {
                        time: '10:49:20',
                        code: '000001',
                        name: '平安银行',
                        price: '104',
                        occount: 16,
                        direction: '买入',
                        status: 1
                    },
                    {
                        time: '11:10:05',
                        code: '000001',
                        name: '平安银行',
                        price: '102',
                        occount: 20,
                        direction: '买入',
                        status: 2
                    },
                    {
                        time: '11:13:27',
                        code: '000001',
                        name: '平安银行',
                        price: '108',
                        occount: 28,
                        direction: '买入',
                        status: 1
                    },
                ],

                dataTotalCount: 9,//记录总条数
                query: {
                    currentPage: 1,//当前页码
                    pageSize: 4,//每页显示记录数
                }
            }
        },
        methods: {
            handlePageChange(val) {
                //this.query.currentPage = val;
                //vue采用赋值的方式，不采用上述方式
                this.$set(this.query, 'currentPage', val);
            },
            //可对每个单元格设置样式
            cellStyle({row, column, rowIndex, columnIndex}) {
                return "padding:2px";
            },
            //处理排序时间
            changeTableSort(column) {
                let fieldName = column.prop;
                let sortingType = column.order;
                console.log("fieldName=" + fieldName);
                console.log("sortingType=" + sortingType);
                //对于时间的排序不同于数值的排序
                if (fieldName == 'time') {
                    if (sortingType == "descending") {
                        this.tableData = this.tableData.sort(
                            (a, b) => {
                                if (b[fieldName] > a[fieldName]) {
                                    return 1;
                                } else if (b[fieldName] = a[fieldName]) {
                                    return 0;
                                } else {
                                    return -1;
                                }
                            }
                        );
                    } else {
                        this.tableData = this.tableData.sort(
                            (a, b) => {
                                if (b[fieldName] > a[fieldName]) {
                                    return -1;
                                } else if (b[fieldName] = a[fieldName]) {
                                    return 0;
                                } else {
                                    return 1;
                                }
                            }
                        );
                    }
                }
            }
        }
    }
</script>

<style scoped>

</style>