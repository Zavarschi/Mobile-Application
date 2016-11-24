import React, {Component} from 'react';
import {
    AppRegistry,
    StyleSheet,
    Text,
    View,
    ListView,
    Button,
    Navigator,
    TouchableOpacity,
    TextInput,
    Alert,
} from 'react-native';


export default class ListOfItems extends Component {
    constructor(props) {
        super(props);
        const ds = new ListView.DataSource({rowHasChanged: (r1, r2) => r1 !== r2});
        this.state = {
            ds: [{Car: "Opel"}, {Car: "Audi"}, {Car: "Ford"}],
            dataSource: ds,
        };
        this.goBack = () => {
            this.props.navigator.push({id: 1});
        };

        if (this.props.changed != null) {
            this.state.ds = this.props.cars;
            for(let i=0; i < this.props.cars.length; i++){
                if(this.state.ds[i].Car == this.props.before){
                    this.state.ds[i].Car = this.props.changed;
                    break;
                }
            }
        }
    }

    componentDidMount() {
        this.setState({
            dataSource: this.state.dataSource.cloneWithRows(this.state.ds),
        })

    }

    pressRow(rowData) {
        this.props.navigator.push({id: 4, car: rowData.Car, cars: this.state.ds});
    }

    renderRow(rowData) {
        return (
            <TouchableOpacity
                onPress={()=> this.pressRow(rowData)}
                underlayColor='#ddd'>
              <View style={styles.row}>
                <Text style={{fontSize:18}}>{rowData.Car} </Text>
              </View>
            </TouchableOpacity>
        )
    }

    render() {
        return (
            <View style={styles.container}>
              <ListView
                  dataSource={this.state.dataSource}
                  renderRow={this.renderRow.bind(this)}/>
              <View
                  style={{width: 140, height: 20,paddingVertical: 10, paddingHorizontal: 20, backgroundColor: 'transparent'}}>
              </View>
              <TouchableOpacity onPress={this.goBack}>
                <View
                    style={{alignItems: 'center',width: 150, height: 50,paddingVertical: 10, paddingHorizontal: 20, backgroundColor: '#282828'}}>
                  <Text style={styles.buttontext}>Go back</Text>
                </View>
              </TouchableOpacity>
            </View>
        );
    }
}


const styles = StyleSheet.create({
    container: {
        flex: 1,
        alignItems: 'center',
    },
    welcome: {
        fontSize: 20,
        textAlign: 'center',
        color: 'white',
    },
    buttontext: {
        fontSize: 20,
        textAlign: 'center',
        color: 'white',
    },
    choseonetext: {
        fontSize: 30,
        color: "black"
    },
    row: {
        flex: 1,
        flexDirection: 'row',
        padding: 18,
        borderBottomWidth: 1,
        borderColor: '#d7d7d7',
    },
    selectionText: {
        fontSize: 15,
        paddingTop: 3,
        color: '#282828',
        textAlign: 'right'
    }
});