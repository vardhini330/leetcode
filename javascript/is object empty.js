/**
 * @param {Object|Array} obj
 * @return {boolean}
 */
var isEmpty = function(obj) {
    for (const k in obj) {
        return false;
    }
    return true;
};
