import axios from 'axios';

export default {

    list() {
        return axios.get('/groups')
        //  return 
        // let groups = [
        //     {
        //         "groupId":"54358380198019238113123123243489199574",
        //         "name": "Party Supplies",
        //         "description": "Group for party supplies lists and the people that make the parties happen",
        //     },
        //     {
        //         "groupId": "54358380198019238113123123243489199575",
        //         "name": "Phi Rho Alpha Tao",
        //         "description": "Supplies for the house yo",
        //     },
        //     {
        //         "groupId": "6789998218",
        //         "name": "Mickey Mouse Clubhouse",
        //         "description": "Dave's favorite"
        //     }
        // ];
        // return {
        //     "data": groups
        // };
    }
}
