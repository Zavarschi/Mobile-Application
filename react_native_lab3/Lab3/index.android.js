/**
 * React Native Navigation App
 * https://github.com/jlsuarezs/ReactNativeNavigationExample
 * @flow
 */

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
    Image,
    Alert,
} from 'react-native';

import ListOfItems from './list.js';
import EditItem from './details.js';
import EmailScreen from './email';


var PageOne = React.createClass({

    onListButtonPress(){
        this.props.navigator.push({id: 2});
    },
    onEmailButtonPress(){
        this.props.navigator.push({id: 3});
    },

    render() {
        return (
            <View style={[styles.container, {backgroundColor: 'white'}]}>
                <Text style={styles.choseonetext}>Chose one</Text>
                <TouchableOpacity onPress={this.onListButtonPress}>
                    <View
                        style={{alignItems: 'center',width: 150, height: 50,paddingVertical: 10, paddingHorizontal: 20, backgroundColor: '#841584'}}>
                        <Text style={styles.buttontext}>List</Text>
                    </View>
                </TouchableOpacity>
                <View
                    style={{width: 140, height: 20,paddingVertical: 10, paddingHorizontal: 20, backgroundColor: 'white'}}>
                </View>

                <TouchableOpacity onPress={this.onEmailButtonPress}>
                    <View
                        style={{alignItems: 'center',width: 150, height: 50,paddingVertical: 10, paddingHorizontal: 20, backgroundColor: '#282828'}}>
                        <Text style={styles.buttontext}>Email</Text>
                    </View>
                </TouchableOpacity>
            </View>
        )
    },
});

const ds = new ListView.DataSource({rowHasChanged: (r1, r2) => r1 !== r2});



class ReactNativeNavigationExample extends Component {
    _renderScene(route, navigator) {
        if (route.id === 1) {
            return <PageOne navigator={navigator}/>
        } else if (route.id === 2) {
            return <ListOfItems navigator={navigator} changed={route.changed} before={route.before} cars={route.cars}/>
        } else if (route.id === 3) {
            return <EmailScreen navigator={navigator}/>
        } else if (route.id == 4) {
            return <EditItem navigator={navigator} car={route.car} cars={route.cars} />
        }
    }

    render() {
        return (
            <Navigator
                initialRoute={{id: 1, }}
                renderScene={this._renderScene}/>
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

AppRegistry.registerComponent('Lab3', () => ReactNativeNavigationExample);