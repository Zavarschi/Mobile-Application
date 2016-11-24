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


export default class EditItem extends Component {
    constructor(props) {
        super(props);
        this.state = {text: this.props.car.toString()};
        this.goBack = () => {
            this.props.navigator.push({id: 2, cars:this.props.cars, changed: this.state.text, before: this.props.car.toString()});
        }
    }

    render() {
        return (
            <View style={styles.container}>
              <TextInput
                  style={{color:'black', fontSize:50, width:200}}
                  onChangeText={(text) => this.setState({text})}
                  value={this.state.text}
              />
              <TouchableOpacity onPress={this.goBack}>
                <View
                    style={{alignItems: 'center',width: 150, height: 50,paddingVertical: 10, paddingHorizontal: 20, backgroundColor: '#282828'}}>
                  <Text style={styles.buttontext}>Save</Text>
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