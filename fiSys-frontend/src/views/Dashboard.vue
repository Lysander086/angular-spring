<template>
    <div>
        <!--信息提示-->
        <div class="crumbs">
            <el-breadcrumb>
                <el-breadcrumb-item>
                    <i class="el-icon-baseball"></i>资金股份
                </el-breadcrumb-item>
            </el-breadcrumb>
        </div>

        <!--2020-11-17 引入资金持仓组件-->
        <el-card class="container">
            <posi-list/>
        </el-card>
    </div>
</template>

<script>

    import PosiList from "../components/PosiList";

    export default {
        name: "Dashboard.vue",
        components:{
            PosiList
        }
    }
</script>

<style scoped>

</style>