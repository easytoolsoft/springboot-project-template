var AppCtx = {
    env: 'dev', //dev|prod
    version: '2.0',
    ctxPath: '',
    config: {
        pageSize: 50
    },
    global: {},
    utils: {
        debug: function (msg) {
            if (AppCtx.env == "prod") {
                return;
            }
            var time = moment().format("YYYY MM DD HH:mm:ss:SSS")
            console.trace('[%s]:%s', time, msg);
        }
    },
    validate: {},
    temp: {}
};
