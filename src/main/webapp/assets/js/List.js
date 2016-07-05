/**
 * Simple ArrayList
 */

function List() {
    this.arr = new Array();
    this.push =  function (key){
        this.arr.push(key);
    };
    this.isExist = function ( key ) {
        for (var i = 0; i < this.arr.length; i++) {
            if (this.arr[i] == key)
                return true;
        }
        return false;
    };
    this.eliminate = function ( key ){
        var i;
        for (i = 0; i < this.arr.length; i++) {
            if (this.arr[i] == key)
                break;
        }
        for(; i < this.arr.length - 1; i++){
            this.arr[i] = this.arr[i+1];
        }
        this.arr.pop();
    }

}
