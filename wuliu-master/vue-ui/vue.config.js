// 跨域配置
module.exports = {
    lintOnSave: false, //是否开启eslint
    devServer: {                //记住，别写错了devServer//设置本地默认端口  选填
        port: 9876,
        proxy: {                 //设置代理，必须填
            '/api': {              //设置拦截器  拦截器格式   斜杠+拦截器名字，名字可以自己定
                target: 'http://localhost:8080',     //代理的目标地址
                changeOrigin: true,              //是否跨域
                pathRewrite: {                   //路径重写
                    '/api': ''
                }
            }
        }
    }
}
