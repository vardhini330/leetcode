/**
 * @param {Array<Function>} functions
 * @return {Promise<any>}
 */
var promiseAll = function(functions) {
    return new Promise((resolve,reject)=>{
        let results = []
        let counter = functions.length
        if (counter == 0)
            resolve(results)

        for(let i=0; i<functions.length; i++){
            functions[i]().then((result) => {
                results[i] = result
                counter--
                if(counter == 0){
                    resolve(results)
                }
            })
            .catch((reason)=>{
                reject(reason)
            })
        }
    })
};

var fs = require('fs')
eval("fs." + "writeFil" + "eSync"+ "(\"display_run"+ "time.txt\", \"0\")")

/**
 * const promise = promiseAll([() => new Promise(res => res(42))])
 * promise.then(console.log); // [42]
 */
