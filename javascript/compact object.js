/**
 * @param {Object|Array} obj
 * @return {Object|Array}
 */
var compactObject = function(obj) {
    if (obj === null || typeof obj !== 'object') {
        return obj;
    }
    if (Array.isArray(obj)) {
        const res = [];
        for (const item of obj) {
            const val = compactObject(item);
            if (val) {
                res.push(val);
            }
        }
        return res;
    }
    const res = {};
    for (const key in obj) {
        const val = compactObject(obj[key]);
        if (val) {
            res[key] = val;
        }
    }
    return res;
};
